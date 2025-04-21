package com.thang.profile.service;

import com.thang.profile.dto.keycloak.AccessTokenResponse;
import com.thang.profile.dto.keycloak.CredentialsRequest;
import com.thang.profile.dto.keycloak.ExchangeTokenParam;
import com.thang.profile.dto.keycloak.UserCreationRequest;
import com.thang.profile.dto.request.ProfileCreationRequest;
import com.thang.profile.dto.response.ProfileResponse;
import com.thang.profile.entity.Profile;
import com.thang.event.NotificationEvent;
import com.thang.profile.exception.AppException;
import com.thang.profile.exception.ErrorCode;
import com.thang.profile.exception.ErrorNormalizer;
import com.thang.profile.mapper.ProfileMapper;
import com.thang.profile.repository.ProfileRepository;
import com.thang.profile.repository.keyCloakClient.KeyCloakClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileMapper profileMapper;
    private final KeyCloakClient keyCloakClient;
    private final ProfileRepository profileRepository;
    private final ErrorNormalizer errorNormalizer;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${idp.client-id}")
    private String clientId;

    @Value("${idp.client-secret}")
    private String clientSecret;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<ProfileResponse> getProfiles() {
        return profileRepository.findAll().stream()
                .map(profileMapper::toProfileResponse).collect(Collectors.toList());
    }

    public ProfileResponse getProfile(){
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new AppException(ErrorCode.PROFILE_NOT_FOUND));

        return profileMapper.toProfileResponse(profile);
    }


    public ProfileResponse createProfile(ProfileCreationRequest request){
        try {
            // create account from keycloak
            // exchangeToken
            AccessTokenResponse token = keyCloakClient.exchangeToken(ExchangeTokenParam.builder()
                            .client_id(clientId)
                            .client_secret(clientSecret)
                            .grant_type("client_credentials")
                    .build());


            List<CredentialsRequest> credentials = new ArrayList<>();
            credentials.add(CredentialsRequest.builder()
                            .type("password")
                            .value(request.getPassword())
                            .temporary(false)
                    .build());

            // create user from keycloak
            var response = keyCloakClient.createUser(UserCreationRequest.builder()
                            .email(request.getEmail())
                            .username(request.getUsername())
                            .lastName(request.getLastName())
                            .firstName(request.getFirstName())
                            .enabled(true)
                            .totp(false)
                            .emailVerified(false)
                            .credentials(credentials)
                    .build(),
                    "Bearer " + token.getAccessToken()
            );
            // get user info from keycloak
            String userId = getUserIdFromLocation(Objects.requireNonNull(response.getHeaders().getLocation()).toString());

            // create profile
            // map request to entity
            Profile profile = profileMapper.toProfile(request);
            profile.setUserId(userId);
            profile = profileRepository.save(profile);

            NotificationEvent notificationEvent = NotificationEvent.builder()
                    .channel("EMAIL")
                    .recipient(request.getEmail())
                    .subject("Welcome to bookteria")
                    .body("Hello, " + request.getUsername())
                    .build();

            // Publish message to kafka
            kafkaTemplate.send("notification-delivery2", notificationEvent);

            return profileMapper.toProfileResponse(profile);
        }
        catch (FeignException exception){
            throw errorNormalizer.handleKeyCloakException(exception);
        }
    }

    private String getUserIdFromLocation(String url){
        if(url.isEmpty()) throw new AppException(ErrorCode.INVALID_CREATE_USER);
        String[] split = url.split("/");
        return split[split.length - 1];
    }

    public ProfileResponse getUserProfile(String userId){
        Profile profile = profileRepository.findByUserId(userId).orElseThrow(() -> new AppException(ErrorCode.PROFILE_NOT_FOUND));
        return profileMapper.toProfileResponse(profile);
    }
}
