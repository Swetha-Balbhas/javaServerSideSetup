package com.om.springboot.service.admin;


import com.om.springboot.dto.mapper.user.AdminProfileDtoMapper;
import com.om.springboot.dto.model.user.AdminProfileDto;
import com.om.springboot.dto.model.user.UserProfileDto;
import com.om.springboot.mappers.user.AdminProfileMapper;
import com.om.springboot.mappers.user.UserAuthenticationMapper;
import com.om.springboot.mappers.user.UserProfileMapper;
import com.om.springboot.model.user.AdminProfile;
import com.om.springboot.model.user.UserAuthentication;
import com.om.springboot.model.user.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AdminProfileServiceImpl implements AdminProfileService {

    @Autowired
    @Qualifier("adminProfileMapper")
    private AdminProfileMapper adminProfileMapper;

    @Override
    public Boolean existsByEmailAndRoleId(String email, Long roleId){
       Boolean isExists = adminProfileMapper.findByEmailAndRoleId(email, roleId);
       if(null != isExists && isExists){
           return Boolean.TRUE;
       } else{
           return false;
       }
    }

    @Override
    public AdminProfileDto getProfileByEmail(String email){
       return AdminProfileDtoMapper.toAdminProfileDto(adminProfileMapper.getProfileByEmail(email));

    }


}