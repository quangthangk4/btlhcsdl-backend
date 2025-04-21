package com.thang.profile.dto.keycloak;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CredentialsRequest {
    private String type;
    private String value;
    private Boolean temporary;
}
