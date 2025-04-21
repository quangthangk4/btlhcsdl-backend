package com.thang.profile.controller;

import com.thang.profile.dto.ApiResponse;
import com.thang.profile.dto.request.ProfileCreationRequest;
import com.thang.profile.dto.response.ProfileResponse;
import com.thang.profile.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping("/registration")
    public ApiResponse<ProfileResponse> createProfile(@RequestBody ProfileCreationRequest request) {
        return ApiResponse.<ProfileResponse>builder()
                .result(profileService.createProfile(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<ProfileResponse>> getAllProfiles() {
        return ApiResponse.<List<ProfileResponse>>builder()
                .result(profileService.getProfiles())
                .build();
    }

    @GetMapping("/my-profile")
    public ApiResponse<ProfileResponse> getMyProfile() {
        return ApiResponse.<ProfileResponse>builder()
                .result(profileService.getProfile())
                .build();
    }

    @GetMapping("/{userId}")
    public ApiResponse<ProfileResponse> getProfile(@PathVariable String userId) {
        return ApiResponse.<ProfileResponse>builder()
                .result(profileService.getUserProfile(userId))
                .build();
    }
}
