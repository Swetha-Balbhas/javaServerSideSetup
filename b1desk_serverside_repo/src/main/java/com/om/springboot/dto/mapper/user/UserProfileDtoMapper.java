package com.om.springboot.dto.mapper.user;


import com.om.springboot.dto.model.user.UserProfileDto;
import com.om.springboot.model.user.UserProfile;

import java.util.ArrayList;
import java.util.List;

public class UserProfileDtoMapper {
    public static UserProfileDto toUserProfileDto(UserProfile userProfile) {
        return new UserProfileDto()
                .setUserId(userProfile.getUserId())
                .setFirstName(userProfile.getFirstName())
                .setLastName(userProfile.getLastName())
                .setMobile(userProfile.getMobile())
                .setEmail(userProfile.getEmail())
                .setDepartment(userProfile.getDepartment())
                .setLandline(userProfile.getLandline())
                .setCompany(userProfile.getCompany())
                .setAddress(userProfile.getAddress())
                .setCountry(userProfile.getCountry())
                .setCity(userProfile.getCity())
                .setPincode(userProfile.getPincode())
                .setPassword(userProfile.getPassword())
                .setCreatedAt(userProfile.getCreatedAt());
    }

    public static List<UserProfileDto> toUserProfileDtoList(List<UserProfile> list){
            if(null == list || list.isEmpty()) return null;
            List<UserProfileDto> dtoList = new ArrayList<UserProfileDto>();
            for(UserProfile user : list) {
                dtoList.add(toUserProfileDto(user));
            }
            return dtoList;
        }
    }

