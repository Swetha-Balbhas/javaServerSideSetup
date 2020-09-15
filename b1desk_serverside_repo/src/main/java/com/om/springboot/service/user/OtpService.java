package com.om.springboot.service.user;


import com.om.springboot.dto.model.user.OtpDto;

public interface OtpService {
    public String generateSixDigitInteger();
    String insertOtp(String email);
    String updateOtp(String email);

    public Boolean existsByEmail(String email);

    public Boolean existsByEmailAndOtp(String email, String otp);

    public OtpDto getOtpByEmail(String email);

    String updateResendSixDigitOtp(String email);
}