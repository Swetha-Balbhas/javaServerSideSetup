package com.om.springboot.service.user;


import com.om.springboot.dto.model.user.UserProfileDto;

import java.util.List;

public interface UserProfileService {

    Boolean existsByEmail(String email);
    Boolean createProfile(UserProfileDto userProfileDto);

    public List<UserProfileDto> getAllUsers();
    UserProfileDto getUserProfileByEmail(String email);
}