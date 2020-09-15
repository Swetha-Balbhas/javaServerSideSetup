package com.om.springboot.service.user;

import com.om.springboot.dto.model.user.UserAuthenticationDto;

public interface UserAuthenticationService {
    public UserAuthenticationDto getUserAuthenticationById(Long id);
    Boolean updateUserAuthentication(String email);
    Boolean ExistsByIdAndPassword(Long id, String password);
    Boolean resetPassword(Long id, String encodedPassword);
}

