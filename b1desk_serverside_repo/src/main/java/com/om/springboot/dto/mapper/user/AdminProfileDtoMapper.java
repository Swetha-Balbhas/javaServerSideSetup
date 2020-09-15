package com.om.springboot.dto.mapper.user;


import com.om.springboot.dto.model.user.AdminProfileDto;
import com.om.springboot.model.user.AdminProfile;

public class AdminProfileDtoMapper {
    public static AdminProfileDto toAdminProfileDto(AdminProfile adminProfile) {
        return new AdminProfileDto()
                .setUserId(adminProfile.getUserId())
                .setName(adminProfile.getName())
                .setMobile(adminProfile.getMobile())
                .setEmail(adminProfile.getEmail())
                .setRoleId(adminProfile.getRoleId())
                .setCreatedAt(adminProfile.getCreatedAt());
    }
}
