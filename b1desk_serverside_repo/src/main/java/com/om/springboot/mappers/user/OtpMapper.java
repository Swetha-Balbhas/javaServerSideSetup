package com.om.springboot.mappers.user;


import com.om.springboot.model.user.Otp;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OtpMapper {

    Boolean insertOtp(Otp otp);

    Otp getOtpById(Long id);

    Otp getOtpByMobile(String mobile);

    Otp getOtpByEmail(String email);

    Boolean findByMobile(String mobile);

    Boolean findByEmail(String email);

    Otp getMobileByMobile(String mobile);

    Boolean updateOtp(Otp otp);

    Otp getOtpByUserId(Long userId);

    Boolean findByUserId(Long userId);

    Boolean findByMobileAndOtp(String mobile, String otp);

    Boolean findByEmailAndOtp(String email, String otp);

}
