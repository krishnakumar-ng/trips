package com.trips.api.gateway.filter;

import com.trips.api.gateway.data.dto.VerifyTokenResponseDto;
import com.trips.api.gateway.exception.AuthorizationException;
import com.trips.api.gateway.exception.MissingAuthTokenException;
import com.trips.api.gateway.feign.client.AuthClient;
import com.trips.api.gateway.util.EurekaClientUtil;
import com.trips.api.gateway.validator.RouteValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
@Slf4j
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private WebClient client;
    @Autowired
    private RouteValidator routeValidator;
    @Autowired
    private EurekaClientUtil eurekaClientUtil;
//    @Autowired
//    private AuthClient authClient;

    public AuthenticationFilter(){
        super(Config.class);
    }

    @Value("${services.auth-service.id}")
    String authServiceID;

    @Value("${services.auth-service.v1.api}")
    String authServiceV1;

    @Value("${services.auth-service.v1.name}")
    String authServiceV1Name;

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (!routeValidator.isSecured.test(exchange.getRequest())) {
                return chain.filter(exchange);
            }
            try {
                final String bearerToken = getBearerTokenFromServerHttpRequest(exchange.getRequest());
                return verifyToken(bearerToken)
                        .flatMap(verifyToken -> {
                            log.info("verified....");
                            return chain.filter(exchange);
                        }).onErrorResume(exception -> {
                            ServerHttpResponse httpResponse = exchange.getResponse();
                            if (exception instanceof WebClientResponseException webClientResponseException) {
                                // get the error json response
                                String errorJson = webClientResponseException.getResponseBodyAsString();
                                log.info("errorJson is: {}", errorJson);
                                exchange.getResponse().setStatusCode(webClientResponseException.getStatusCode());
                                return exchange.getResponse().writeWith(
                                        Mono.just(exchange.getResponse().bufferFactory().wrap(errorJson.getBytes()))
                                );
                            }
                            exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
                            return exchange.getResponse().writeWith(
                                    Mono.just(exchange.getResponse().bufferFactory().wrap(exception.getMessage().getBytes()))
                            );
                        });
            } catch (Exception ex) {
                exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
                return exchange.getResponse().writeWith(
                        Mono.just(exchange.getResponse().bufferFactory().wrap(ex.getMessage().getBytes()))
                );
            }
        };
    }

    private String getBearerTokenFromServerHttpRequest(ServerHttpRequest request) {
        // check if header contains authorization token in header or not
        if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
            throw new AuthorizationException("Missing Authorization header.");
        }
        final String authHeader = Objects.requireNonNull(request.getHeaders().get(HttpHeaders.AUTHORIZATION)).getFirst();
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new MissingAuthTokenException("Authorization token is missing");
        }
        return authHeader;
    }

    private Mono<VerifyTokenResponseDto> verifyToken(String bearerToken) {
        log.info("Calling auth-service Service for verifying the token: {}", bearerToken);

//        VerifyTokenResponseDto verifyTokenResponse = authClient.verifyToken(bearerToken).getBody();
//
//        return Mono.just(verifyTokenResponse);

        // Construct URL
        String organizationServiceURI = eurekaClientUtil.getServiceUri(authServiceID);
        String url = organizationServiceURI + authServiceV1 + "/verify-token";

        return client.post()
                .uri(url)
                .header("Authorization", bearerToken)
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(VerifyTokenResponseDto.class);
    }

    public static class Config {
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
