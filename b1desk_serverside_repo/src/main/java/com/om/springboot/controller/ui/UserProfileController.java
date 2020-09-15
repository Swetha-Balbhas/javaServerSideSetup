package com.om.springboot.controller.ui;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.om.springboot.controller.request.*;
import com.om.springboot.dto.model.user.CitiesDto;
import com.om.springboot.dto.model.user.CountriesDto;
import com.om.springboot.dto.model.user.UserAuthenticationDto;
import com.om.springboot.dto.model.user.UserProfileDto;
import com.om.springboot.dto.response.ApiResponse;
import com.om.springboot.dto.response.user.CapchaCodeResponse;
import com.om.springboot.dto.response.user.CitiesResponse;
import com.om.springboot.dto.response.user.OtpResponse;
import com.om.springboot.service.application.AppMailerService;
import com.om.springboot.service.application.ExchangeRate;
import com.om.springboot.service.user.*;
import com.om.springboot.util.ErrorConstants;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/b1desk")
public class UserProfileController {

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    OtpService otpService;

    @Autowired
    CountriesService countriesService;

    @Autowired
    CitiesService citiesService;

    @Autowired
    CapchaCodeService capchaCodeService;

    @Autowired
    UserAuthenticationService userAuthenticationService;

    @Autowired
    AppMailerService appMailerService;

    @Autowired
    ExchangeRate exchangeRate;

    @CrossOrigin
    @PostMapping("preLogin/registeration")
    public ResponseEntity<?> registerNewUser(@Valid @RequestBody UserRegistrationRequest userRegistrationRequest) {
        String email = userRegistrationRequest.getEmail();

        if (userProfileService.existsByEmail(email)) {
            System.out.println(".....> email is already registered....." + email);
            return new ResponseEntity(new ApiResponse(false, ErrorConstants.E101.name()),
                    HttpStatus.OK);
        } else {
            String otpNumber = null;
            if (null != email) {
                Boolean emailSuccess = otpService.existsByEmail(email);
                if (null != emailSuccess && emailSuccess) {
                    otpNumber = otpService.updateOtp(email);
                } else {
                    otpNumber = otpService.insertOtp(email);
                }
                if (null != otpNumber) {
                    Long start = System.currentTimeMillis();
                  //  appMailerService.sendOtpEmail(email, otpNumber);
                    System.out.println(".............> sending otp mail to customer to the email ......" + email + " " + start);
                    OtpResponse otpResponse = new OtpResponse(Boolean.TRUE, "")
                            .setOtp(otpNumber);
                    return new ResponseEntity(otpResponse, HttpStatus.OK);
                } else {
                    return new ResponseEntity(new ApiResponse(false, ErrorConstants.E102.name()),
                            HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity(new ApiResponse(false, ErrorConstants.E100.name()),
                HttpStatus.OK);
    }


    @CrossOrigin
    @PostMapping("/preLogin/validateEmailAndOTP")
    public ResponseEntity<?> validateOTP(@Valid @RequestBody ValidateEmailAndOtp validateEmailAndOtp) {
        String email = validateEmailAndOtp.getEmail();
        String otp = validateEmailAndOtp.getOtp();

        if (null != email)
            if (null != otp) {
                Boolean isValidate = otpService.existsByEmailAndOtp(email, otp);
                if (null != isValidate && isValidate) {
                    return new ResponseEntity(new ApiResponse(true, ""), HttpStatus.OK);
                } else {
                    return new ResponseEntity(new ApiResponse(false, ErrorConstants.E103.name()),
                            HttpStatus.OK);
                }
            }
        return new ResponseEntity(new ApiResponse(false, ErrorConstants.E103.name()),
                HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/preLogin/resendOtpToCustomer")
    public ResponseEntity<?> resendOtp(@Valid @RequestBody UserRegistrationRequest userRegistrationRequest) {
        String email = userRegistrationRequest.getEmail();
        if (userProfileService.existsByEmail(email)) {
            System.out.println(".....> email is already registered....." + email);
            return new ResponseEntity(new ApiResponse(false, ErrorConstants.E101.name()),
                    HttpStatus.OK);
        } else {
            String otpNumber = null;
            if (null != email) {
                Boolean isEmailExistsInOTPTable = otpService.existsByEmail(email);
                if (null != isEmailExistsInOTPTable && isEmailExistsInOTPTable) {

                    otpNumber = otpService.updateResendSixDigitOtp(email);

                    if (null != otpNumber) {
                        OtpResponse otpResponse = new OtpResponse(Boolean.TRUE, "")
                                .setOtp(otpNumber);
                        return new ResponseEntity(otpResponse, HttpStatus.OK);
                    } else {
                        return new ResponseEntity(new ApiResponse(false, ErrorConstants.E105.name()),
                                HttpStatus.OK);
                    }
                } else {
                    return new ResponseEntity(new ApiResponse(false, ErrorConstants.E104.name()), HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity(new ApiResponse(false, ErrorConstants.E104.name()), HttpStatus.OK);
    }


    @CrossOrigin
    @PostMapping("/preLogin/insertRegisteredCustomerDetail")
    public ResponseEntity<?> insertCustomerDetail(@Valid @RequestBody UserRegistrationRequest userRegistrationRequest) {

        Boolean isEmailExists = userProfileService.existsByEmail(userRegistrationRequest.getEmail());

        if (null != isEmailExists && isEmailExists) {
            return new ResponseEntity(new ApiResponse(false, ErrorConstants.E101.name()), HttpStatus.OK);
        } else {
            String password = userRegistrationRequest.getPassword();
            System.out.println("...........> customer entered password.....>" + password);
            String encodedPassword = new BCryptPasswordEncoder().encode(password);
            System.out.println("<..........encodedPassword..........>" + encodedPassword);

            UserProfileDto userProfileDto = new UserProfileDto()
                    .setFirstName(userRegistrationRequest.getFirstName())
                    .setLastName(userRegistrationRequest.getLastName())
                    .setEmail(userRegistrationRequest.getEmail())
                    .setDepartment(userRegistrationRequest.getDepartment())
                    .setMobile(userRegistrationRequest.getMobileCountryCode() + "-" + userRegistrationRequest.getMobile())
                    .setLandline(userRegistrationRequest.getLandlineCountryCode() + "-" + userRegistrationRequest.getLandline())
                    .setCompany(userRegistrationRequest.getCompany())
                    .setAddress(userRegistrationRequest.getAddress())
                    .setCountry(userRegistrationRequest.getCountry())
                    .setCity(userRegistrationRequest.getCity())
                    .setPincode(userRegistrationRequest.getPincode())
                    .setCreatedAt(Instant.now())
                    .setPassword(encodedPassword);

            Boolean isInserted = userProfileService.createProfile(userProfileDto);

            if (null != isInserted && isInserted) {
                return new ResponseEntity(new ApiResponse(true, ""), HttpStatus.OK);
            } else {
                return new ResponseEntity(new ApiResponse(false, ErrorConstants.E100.name()), HttpStatus.OK);
            }
        }
    }


    @GetMapping("/preLogin/getCitiesByCountry/{country}")
    public ResponseEntity<?> getCitiesList(@PathVariable String country) {
        Boolean searchSuccess = countriesService.existsByCountry(country);
        if (null != searchSuccess && searchSuccess) {
            CountriesDto countriesDto = countriesService.getCountryByCountry(country);

            if (null != countriesDto && null != countriesDto.getId()) {
                List<CitiesDto> citiesList = citiesService.getCities(countriesDto.getId());
                if (null == citiesList || citiesList.isEmpty()) {
                    return new ResponseEntity(new ApiResponse(false, ErrorConstants.E106.name()), HttpStatus.OK);
                } else {
                    CitiesResponse citiesResponse = new CitiesResponse(true, "")
                            .setCountry(country)
                            .setCities(citiesList.stream().map(dto -> dto.getCity()).collect(Collectors.toList()));
                    return new ResponseEntity(citiesResponse, HttpStatus.OK);
                }

            } else {
                return new ResponseEntity(new ApiResponse(false, ErrorConstants.E107.name()), HttpStatus.OK);
            }
        } else {
            return new ResponseEntity(new ApiResponse(false, ErrorConstants.E107.name()), HttpStatus.OK);
        }
    }


    /*
    @GetMapping("/preLogin/generateRandomCapchaCode")
    public String generateCapcha(){
      // String captcha = generateCapcha();
     String captcha =   capchaCodeService.generateCaptchaString();
       return captcha.substring(0,6);
    }
*/

    @CrossOrigin
    @GetMapping("/preLogin/generateRandomCapchaCode")
    public ResponseEntity<?> generateCatcha() {
        String capcha = capchaCodeService.generateCaptchaString();
        String sixDigitCaptcha = capcha.substring(0, 6);
        CapchaCodeResponse capchaCodeResponse = new CapchaCodeResponse(true, "")
                .setCapcha(sixDigitCaptcha);
        return new ResponseEntity(capchaCodeResponse, HttpStatus.OK);
    }


    @CrossOrigin
    @PostMapping("/signIn")
    public ResponseEntity<?> loginAsCustomer(@Valid @RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        Boolean isEmailExists = userProfileService.existsByEmail(email);
        System.out.println(".......email is already registered......>" + isEmailExists);
        if (null != isEmailExists && isEmailExists) {
            UserProfileDto userProfileDto = userProfileService.getUserProfileByEmail(email);
            if (null != userProfileDto && null != userProfileDto.getUserId()) {
                UserAuthenticationDto userAuthenticationDto = userAuthenticationService.
                        getUserAuthenticationById(userProfileDto.getUserId());
                String encodedPassword = userAuthenticationDto.getPassword();
                if (new BCryptPasswordEncoder().matches(password, encodedPassword)) {
                    return new ResponseEntity(new ApiResponse(true, ""), HttpStatus.OK);
                } else {
                    return new ResponseEntity(new ApiResponse(false, ErrorConstants.E108.name()), HttpStatus.OK);
                }
            } else {
                return new ResponseEntity(new ApiResponse(false, ErrorConstants.E104.name()),
                        HttpStatus.OK);
            }
        } else {
            return new ResponseEntity(new ApiResponse(false, ErrorConstants.E104.name()),
                    HttpStatus.OK);
        }

    }

    @CrossOrigin
    @PostMapping("/postLogin/verifyEmailAddress")
    public ResponseEntity<?> verifyEmail(@Valid @RequestBody EmailRequest emailRequest) {
        String email = emailRequest.getEmail();
        Boolean isEmailPresents = userProfileService.existsByEmail(email);
        System.out.println(".....email is checks for send otp in controller........>" + isEmailPresents);
        if (null == isEmailPresents || !isEmailPresents) {
            return new ResponseEntity(new ApiResponse(false, ErrorConstants.E104.name()), HttpStatus.OK);
        } else {
            String otpNumber = null;
            Boolean emailSuccess = otpService.existsByEmail(email);
            //System.out.println("............emial is exists checks in controller......" + emailSuccess);
            if (null != emailSuccess && emailSuccess) {
                otpNumber = otpService.updateOtp(email);
            } else {
                otpNumber = otpService.insertOtp(email);
            }
            if (null != otpNumber) {
                OtpResponse otpResponse = new OtpResponse(Boolean.TRUE, "")
                        .setOtp(otpNumber);
                return new ResponseEntity(otpResponse, HttpStatus.OK);
            } else {
                return new ResponseEntity(new ApiResponse(false, ErrorConstants.E102.name()),
                        HttpStatus.OK);
            }
        }
    }

    @CrossOrigin
    @PostMapping("/postLogin/validateOtpForResetPassword")
    public ResponseEntity<?> validateOtp(@Valid @RequestBody ValidateOtpRequest validateOtpRequest) {
        String email = validateOtpRequest.getEmail();
        Boolean isEmailPresents = userProfileService.existsByEmail(email);
        System.out.println(".....email is checks for send otp in controller........>" + isEmailPresents);
        if (null == isEmailPresents || !isEmailPresents) {
            return new ResponseEntity(new ApiResponse(false, ErrorConstants.E104.name()), HttpStatus.OK);
        } else {
            Boolean isOtpValidated = otpService.existsByEmailAndOtp(email, validateOtpRequest.getOtp());
            if (null != isOtpValidated && isOtpValidated) {
                return new ResponseEntity(new ApiResponse(true, ""),
                        HttpStatus.OK);
            } else {
                return new ResponseEntity(new ApiResponse(false, ErrorConstants.E103.name()),
                        HttpStatus.OK);
            }
        }
    }

    @CrossOrigin
    @PostMapping("/postLogin/resetPassword")
    public ResponseEntity<?> forgetPassword(@Valid @RequestBody PasswordResetRequest passwordResetRequest) {
        String email = passwordResetRequest.getEmail();
        String password = passwordResetRequest.getNewPassword();
        Boolean isEmailExists = userProfileService.existsByEmail(email);
        if (null != isEmailExists && isEmailExists) {
            UserProfileDto userProfileDto = userProfileService.getUserProfileByEmail(email);

            String encodedPassword = new BCryptPasswordEncoder().encode(password);
            Boolean isReset = userAuthenticationService.resetPassword(userProfileDto.getUserId(), encodedPassword);
            if (null != isReset && isReset) {
                System.out.println(".........> reset password is ......>" + password);
                System.out.println("........> encoded reset password..........>" + encodedPassword);
                return new ResponseEntity(new ApiResponse(true, ""),
                        HttpStatus.OK);
            } else {
                return new ResponseEntity(new ApiResponse(false, ErrorConstants.E104.name()),
                        HttpStatus.OK);
            }
        }
        return new ResponseEntity(new ApiResponse(false, ErrorConstants.E104.name()),
                HttpStatus.OK);
    }


    @CrossOrigin
    @PostMapping("/postLogin/resendOtp")
    public ResponseEntity<?> resendOtpAfterRegistration(@Valid @RequestBody EmailRequest emailRequest) {
        String email = emailRequest.getEmail();
        Boolean isEmailPresents = userProfileService.existsByEmail(email);
        System.out.println(".....email exists in userTable........>" + isEmailPresents);
        if (null == isEmailPresents || !isEmailPresents) {
            return new ResponseEntity(new ApiResponse(false, ErrorConstants.E104.name()), HttpStatus.OK);
        } else {
            String otpNumber = null;
            if (null != email) {
                Boolean isEmailExistsInOTPTable = otpService.existsByEmail(email);
                if (null != isEmailExistsInOTPTable && isEmailExistsInOTPTable) {

                    otpNumber = otpService.updateResendSixDigitOtp(email);

                    if (null != otpNumber) {
                        OtpResponse otpResponse = new OtpResponse(Boolean.TRUE, "")
                                .setOtp(otpNumber);
                        return new ResponseEntity(otpResponse, HttpStatus.OK);
                    } else {
                        return new ResponseEntity(new ApiResponse(false, ErrorConstants.E109.name()),
                                HttpStatus.OK);
                    }
                } else {
                    return new ResponseEntity(new ApiResponse(false, ErrorConstants.E104.name()), HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity(new ApiResponse(false, ErrorConstants.E104.name()), HttpStatus.OK);
    }



/*
        @CrossOrigin
    @PostMapping("preLogin/registeration")
    public ResponseEntity<?> registerNewUser(@Valid @RequestBody UserRegistrationRequest userRegistrationRequest) {
        String encodedPassword = new BCryptPasswordEncoder().encode(userRegistrationRequest.getPassword());
i
        UserProfileDto userProfileDto = new UserProfileDto()
                .setName(userRegistrationRequest.getName())
                .setDob(userRegistrationRequest.getDob())
                .setGender(userRegistrationRequest.getGender())
                .setMobile(userRegistrationRequest.getMobile())
                .setEmail(userRegistrationRequest.getEmail())
                .setCreatedAt(Instant.now())
                .setPassword(encodedPassword);
        Boolean isProfileCreated = userProfileService.createProfile(userProfileDto);
        if (null != isProfileCreated && isProfileCreated) {
            return new ResponseEntity(new ApiResponse(true, ""),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(new ApiResponse(Boolean.FALSE, ErrorConstants.E100.name()), HttpStatus.OK);
        }
    }
*/
}
