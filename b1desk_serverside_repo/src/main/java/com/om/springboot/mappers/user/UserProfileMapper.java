package com.om.springboot.mappers.user;

import com.om.springboot.model.user.UserProfile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserProfileMapper {
    Boolean insertUserProfile(UserProfile userProfile);

    UserProfile getUserProfileByName(String name);

   Long getMaxUserId();

   List<UserProfile> getAllUserProfile();

    Boolean findByEmail(String email);

    UserProfile getUserProfileByEmail(String email);
}
