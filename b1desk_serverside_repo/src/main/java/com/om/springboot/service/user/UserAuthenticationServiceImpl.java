package com.om.springboot.service.user;

import com.om.springboot.dto.mapper.user.UserAuthenticationDtoMapper;
import com.om.springboot.dto.model.user.AdminProfileDto;
import com.om.springboot.dto.model.user.UserAuthenticationDto;
import com.om.springboot.mappers.user.UserAuthenticationMapper;
import com.om.springboot.model.user.UserAuthentication;

import com.om.springboot.service.admin.AdminProfileService;
import com.om.springboot.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    @Autowired
    @Qualifier("userAuthenticationMapper")
    UserAuthenticationMapper userAuthenticationMapper;

    @Autowired
    AdminProfileService adminProfileService;

@Override
public Boolean ExistsByIdAndPassword(Long id, String password){
    return userAuthenticationMapper.findIdAndPassword(id,password );
}

    @Override
    public Boolean resetPassword(Long id, String encodedPassword) {
        UserAuthentication userAuthentication = userAuthenticationMapper.getUserAuthenticationById(id);
        if (null == userAuthentication) {
            return Boolean.FALSE;

        } else {
            //System.out.println(".....get callcenterId from user Authentication table" + userAuthentication.getCallcenterId());
            Boolean isUpdatePassword =
                    userAuthenticationMapper.updatePassword(userAuthentication.getId(), encodedPassword);

            if (null != isUpdatePassword && isUpdatePassword) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }

        }
    }


    @Override
    public UserAuthenticationDto getUserAuthenticationById(Long id) {
        UserAuthentication userAuthentication = userAuthenticationMapper.getUserAuthenticationById(id);
        return UserAuthenticationDtoMapper.toUserAuthenticationDto(userAuthentication);
    }

    @Override
    public Boolean updateUserAuthentication(String email) {
        AdminProfileDto adminProfileDto = adminProfileService.getProfileByEmail(email);
        Long userId = adminProfileDto.getUserId();
        if (null == userId) return null;
        UserAuthentication userAuthentication = new UserAuthentication();
        userAuthentication.setId(userId);
        userAuthentication.setRetryLoginAttempts(0);
        userAuthentication.setStatus(AppConstants.STATUS_ACTIVE);
        userAuthentication.setLoginAt(Instant.now());
        Boolean success = userAuthenticationMapper.updateUserAuthentication(userAuthentication);
        System.out.println("......> insert Login time in Authentication table...." +success);
        if (null != success && success) {
        return true;
        } else{
            return false;
        }
    }
}