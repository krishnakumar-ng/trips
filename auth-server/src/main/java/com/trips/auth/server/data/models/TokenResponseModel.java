package com.trips.auth.server.data.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponseModel {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("expires_in")
    private int expirationTime;
    @JsonProperty("token_type")
    private String tokenType;
}
