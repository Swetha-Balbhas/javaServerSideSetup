package com.om.springboot.mappers.user;

import com.om.springboot.model.user.UserAuthentication;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserAuthenticationMapper {
   Boolean insertUserAuthentication(UserAuthentication userAuthentication);
   UserAuthentication getUserAuthenticationById(Long id);
  Boolean updateUserAuthentication(UserAuthentication userAuthentication);
   Boolean findIdAndPassword(Long id, String password);
   Boolean updatePassword(Long id, String password);
}
