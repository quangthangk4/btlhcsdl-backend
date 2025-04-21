package com.thang.profile.repository.keyCloakClient;

import com.thang.profile.dto.keycloak.AccessTokenResponse;
import com.thang.profile.dto.keycloak.ExchangeTokenParam;
import com.thang.profile.dto.keycloak.UserCreationRequest;
import feign.Headers;
import feign.QueryMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "create", url = "${idp.url}")
public interface KeyCloakClient {
    @PostMapping(value = "/admin/realms/social_media/users"
    , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> createUser(@RequestBody UserCreationRequest request,
                                 @RequestHeader("Authorization") String authorization);


    @PostMapping(value = "realms/social_media/protocol/openid-connect/token",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    AccessTokenResponse exchangeToken(@QueryMap ExchangeTokenParam request);
}
