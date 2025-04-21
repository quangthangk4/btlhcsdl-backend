package com.thang.post.repository.httpClient;

import com.thang.post.dto.ApiResponse;
import com.thang.post.dto.response.UserProfileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "profile-service", url = "${app.services.profile.url}")
public interface ProfileClient {
    @GetMapping("/users/{userId}")
    ApiResponse<UserProfileResponse> getProfile(@PathVariable String userId);
}
