package com.thang.profile.mapper;

import com.thang.profile.dto.request.ProfileCreationRequest;
import com.thang.profile.dto.response.ProfileResponse;
import com.thang.profile.entity.Profile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    Profile toProfile(ProfileCreationRequest request);
    ProfileResponse toProfileResponse(Profile profile);
}
