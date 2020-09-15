package com.om.springboot.mappers.user;

import com.om.springboot.model.user.AdminProfile;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminProfileMapper {
   Boolean findByEmailAndRoleId(String email, Long roleId);
   AdminProfile getProfileByEmail(String email);
}
