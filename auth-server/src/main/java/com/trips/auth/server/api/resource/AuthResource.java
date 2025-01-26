package com.trips.auth.server.api.resource;

import com.trips.auth.server.constants.ApiConstants;
import com.trips.auth.server.data.entity.UserEntity;
import com.trips.auth.server.data.models.TokenResponseModel;
import com.trips.auth.server.data.models.UserRequestModel;
import com.trips.auth.server.data.models.UserResponseModel;
import com.trips.auth.server.data.models.VerifyTokenResponseModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "authorization-server-resource")
@RequestMapping(ApiConstants.AUTH_ENDPOINT)
public interface AuthResource {

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = ApiConstants.MESSAGE_SUCCESS),
                    @ApiResponse(responseCode = "400", description = ApiConstants.MESSAGE_BAD_REQUEST),
                    @ApiResponse(responseCode = "500", description = ApiConstants.MESSAGE_INTERNAL_SERVER_ERROR),
            }
    )
    @Operation(method = "POST", summary = "Register New User")
    @PostMapping(ApiConstants.REGISTER_ENDPOINT)
    ResponseEntity<UserResponseModel> register(@RequestBody UserRequestModel userRequestModel);

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = ApiConstants.MESSAGE_SUCCESS),
                    @ApiResponse(responseCode = "400", description = ApiConstants.MESSAGE_BAD_REQUEST),
                    @ApiResponse(responseCode = "500", description = ApiConstants.MESSAGE_INTERNAL_SERVER_ERROR),
            }
    )
    @Operation(method = "POST", summary = "Get Access Token")
    @PostMapping(ApiConstants.TOKEN_ENDPOINT)
    ResponseEntity<TokenResponseModel> getAccessToken(@RequestBody UserEntity userEntity);

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = ApiConstants.MESSAGE_SUCCESS),
                    @ApiResponse(responseCode = "400", description = ApiConstants.MESSAGE_BAD_REQUEST),
                    @ApiResponse(responseCode = "500", description = ApiConstants.MESSAGE_INTERNAL_SERVER_ERROR),
                    @ApiResponse(responseCode = "503", description = ApiConstants.MESSAGE_SERVICE_UNAVAILABLE)
            }
    )
    @Operation(method = "POST", summary = "Verify IdToken")
    @PostMapping("/verify-token")
    ResponseEntity<VerifyTokenResponseModel> verifyToken(
            @Parameter(name = "Header", description = "Header") HttpServletRequest httpServletRequest
    );
}
