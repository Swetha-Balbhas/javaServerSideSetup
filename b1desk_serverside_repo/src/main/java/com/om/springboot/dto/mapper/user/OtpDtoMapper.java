package com.om.springboot.dto.mapper.user;

import com.om.springboot.dto.model.user.OtpDto;
import com.om.springboot.model.user.Otp;

public class OtpDtoMapper {

    public static OtpDto toOtpDto(Otp otp) {
        return new OtpDto()
                .setId(otp.getId())
                .setEmail(otp.getEmail())
                .setOtp(otp.getOtp())
                .setCreatedAt(otp.getCreatedAt())
                .setResentAt(otp.getResentAt())
                .setResentCount(otp.getResentCount());


    }
}

