package com.om.springboot.controller.ui;


import com.om.springboot.controller.request.LoginRequest;
import com.om.springboot.dto.model.master.RoleMasterDto;
import com.om.springboot.dto.model.user.AdminProfileDto;
import com.om.springboot.dto.model.user.UserAuthenticationDto;
import com.om.springboot.dto.model.user.UserProfileDto;
import com.om.springboot.dto.response.ApiResponse;
import com.om.springboot.dto.response.admin.Users;
import com.om.springboot.dto.response.admin.UsersResponse;
import com.om.springboot.service.admin.AdminProfileService;
import com.om.springboot.service.application.AppMailerService;
import com.om.springboot.service.master.RoleMasterService;
import com.om.springboot.service.user.UserAuthenticationService;
import com.om.springboot.service.user.UserProfileService;
import com.om.springboot.util.AppConstants;
import com.om.springboot.util.ErrorConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/b1desk")
public class AdminProfileController {

    @Autowired
    RoleMasterService roleMasterService;

    @Autowired
    AdminProfileService adminProfileService;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    UserAuthenticationService userAuthenticationService;

    @Autowired
    AppMailerService appMailerService;

    /*
    @CrossOrigin
    @PostMapping("signIn")
    public ResponseEntity<?> adminSignIn(@Valid @RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        RoleMasterDto roleMasterDto = roleMasterService.getRole(AppConstants.ROLE_ADMIN);

        Boolean doesAdminExist = adminProfileService.existsByEmailAndRoleId(email, roleMasterDto.getRoleId());
        if (null == doesAdminExist || !doesAdminExist) {
            return new ResponseEntity(new ApiResponse(false, ErrorConstants.E120.name()),
                    HttpStatus.OK);
        }
        AdminProfileDto adminProfileDto = adminProfileService.getProfileByEmail(email);
        UserAuthenticationDto userAuthenticationDto = userAuthenticationService.
                getUserAuthenticationById(adminProfileDto.getUserId());
        String encodedPassword = userAuthenticationDto.getPassword();
        System.out.println(".......encoded password.......>" + encodedPassword);

        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        Boolean isAdminExists = bcrypt.matches(password, encodedPassword);
        System.out.println("........check  matches with user entered pw & pwFromDb......" + isAdminExists);
        if (null != isAdminExists && isAdminExists) {
            userAuthenticationService.updateUserAuthentication(email);
            return new ResponseEntity(new ApiResponse(true, ""),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity(new ApiResponse(false, ErrorConstants.E121.name()),
                    HttpStatus.OK);
        }
    }

    @CrossOrigin
    @org.springframework.web.bind.annotation.GetMapping("postLogin/listUsers")
    public ResponseEntity<?> listUsers() {

        List<UserProfileDto> userProfileDtos = userProfileService.getAllUsers();
        if (null == userProfileDtos || userProfileDtos.isEmpty()) {
            return new ResponseEntity(new ApiResponse(false, ErrorConstants.E122.name()),
                    HttpStatus.OK);
        }

        UsersResponse usersResponse = new UsersResponse(Boolean.TRUE, "");
        List<Users> listUsers = new ArrayList<Users>();
        for (UserProfileDto userProfileDto : userProfileDtos) {
            Date date = userProfileDto.getDob();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = dateFormat.format(date);

            Users user = new Users()
                    .setName(userProfileDto.getName())
                    .setDob(strDate)
                    .setGender(userProfileDto.getGender())
                    .setMobile(userProfileDto.getMobile())
                    .setEmail(userProfileDto.getEmail());
            listUsers.add(user);

        }
        usersResponse.setListUsers(listUsers);
        return new ResponseEntity(usersResponse, HttpStatus.OK);
    }



     */

    @CrossOrigin
    @org.springframework.web.bind.annotation.GetMapping("postLogin/calculateAmount/{amount}/{code}")
    public String calculationOnAmount(@PathVariable double amount, @PathVariable Integer code) {
        double pound, euro, yen, ringgit, rupee;
        DecimalFormat f = new DecimalFormat("##.##");
        if (code == 2) {
            // For Dollar Conversion

            rupee = amount * 70;
            System.out.println("Your " + amount + " Dollar is : " + f.format(rupee) + " Ruppes");

            pound = amount * 0.78;
            System.out.println("Your " + amount + " Dollar is : " + f.format(pound) + " Pound");

            euro = amount * 0.87;
            System.out.println("Your " + amount + " Dollar is : " + f.format(euro) + " Euro");

            yen = amount * 111.087;
            System.out.println("Your " + amount + " Dollar is : " + f.format(yen) + " Yen");

            ringgit = amount * 4.17;
            System.out.println("Your " + amount + " Dollar is : " + f.format(ringgit) + " ringgit");

            return "value is viewed in console";

        } else {
            return "entered amount is not dollar";
        }


    }
}
