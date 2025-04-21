package com.thang.profile.dto.keycloak;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccessTokenResponse {
    private String accessToken;
    private String expiresIn;
    private String refreshToken;
    private String refreshExpiresIn;
    private String tokenType;
    private String idToken;
    private String scope;
}
