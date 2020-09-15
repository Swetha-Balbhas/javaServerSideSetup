package com.om.springboot.dto.mapper.user;

import com.om.springboot.dto.model.user.UserAuthenticationDto;
import com.om.springboot.model.user.UserAuthentication;

public class UserAuthenticationDtoMapper {
    public static UserAuthenticationDto toUserAuthenticationDto(UserAuthentication userAuthentication ) {
        return new UserAuthenticationDto()
                .setAuthenticationId(userAuthentication.getAuthenticationId())
                .setId(userAuthentication.getId())
                .setRetryLoginAttempts(userAuthentication.getRetryLoginAttempts())
                .setPassword(userAuthentication.getPassword())
                .setStatus(userAuthentication.getStatus())
                .setLoginAt(userAuthentication.getLoginAt())
                .setLogoutAt(userAuthentication.getLogoutAt());
    }
}
