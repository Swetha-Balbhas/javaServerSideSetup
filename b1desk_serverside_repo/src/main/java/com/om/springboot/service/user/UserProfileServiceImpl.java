package com.om.springboot.service.user;


import com.om.springboot.dto.mapper.user.UserProfileDtoMapper;
import com.om.springboot.dto.model.user.UserProfileDto;
import com.om.springboot.mappers.user.OtpMapper;
import com.om.springboot.mappers.user.UserAuthenticationMapper;
import com.om.springboot.mappers.user.UserProfileMapper;
import com.om.springboot.model.user.UserAuthentication;
import com.om.springboot.model.user.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    @Qualifier("userProfileMapper")
    private UserProfileMapper userProfileMapper;

    @Autowired
    @Qualifier("userAuthenticationMapper")
    private UserAuthenticationMapper userAuthenticationMapper;

    @Override
    public List<UserProfileDto> getAllUsers() {
        return UserProfileDtoMapper.toUserProfileDtoList(userProfileMapper.getAllUserProfile());
    }

    @Override
    public UserProfileDto getUserProfileByEmail(String email){
        return UserProfileDtoMapper.toUserProfileDto(userProfileMapper.getUserProfileByEmail(email));
    }

    @Override
    public Boolean existsByEmail(String email){
        Boolean isEmailExists = userProfileMapper.findByEmail(email);

        if(null != isEmailExists && isEmailExists ){
            return Boolean.TRUE;}
        else{
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean createProfile(UserProfileDto userProfileDto) {
        UserProfile userProfile = new UserProfile();
        userProfile.setFirstName(userProfileDto.getFirstName());
        userProfile.setLastName(userProfileDto.getLastName());
        userProfile.setEmail(userProfileDto.getEmail());
        userProfile.setDepartment(userProfileDto.getDepartment());
        userProfile.setLandline(userProfileDto.getLandline());
        userProfile.setCompany(userProfileDto.getCompany());
        userProfile.setMobile(userProfileDto.getMobile());
        userProfile.setAddress(userProfileDto.getAddress());
        userProfile.setCountry(userProfileDto.getCountry());
        userProfile.setCity(userProfileDto.getCity());
        userProfile.setPincode(userProfileDto.getPincode());
        userProfile.setCreatedAt(userProfileDto.getCreatedAt());
        Boolean isInserted = userProfileMapper.insertUserProfile(userProfile);

        if (null != isInserted && isInserted) {
            Long userId = userProfileMapper.getMaxUserId();
            if (null == userId) {
                return Boolean.FALSE;
            }
            UserAuthentication userAuthentication = new UserAuthentication();
            userAuthentication.setId(userId);
            userAuthentication.setRetryLoginAttempts(0);
            userAuthentication.setStatus(0);
            userAuthentication.setPassword(userProfileDto.getPassword());
            return userAuthenticationMapper.insertUserAuthentication(userAuthentication);
        } else {
            return Boolean.FALSE;
        }
    }

}