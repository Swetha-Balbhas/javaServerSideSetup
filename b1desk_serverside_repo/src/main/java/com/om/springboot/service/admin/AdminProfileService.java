package com.om.springboot.service.admin;


import com.om.springboot.dto.model.user.AdminProfileDto;

public interface AdminProfileService {
    Boolean existsByEmailAndRoleId(String email, Long roleId);

    public AdminProfileDto getProfileByEmail(String email);
}