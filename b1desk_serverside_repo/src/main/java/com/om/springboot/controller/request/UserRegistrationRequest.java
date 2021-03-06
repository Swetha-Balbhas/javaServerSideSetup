package com.om.springboot.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRegistrationRequest {

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    @Size(min = 1, max = 45)
    private String firstName;


    @NotEmpty(message = "{constraints.NotEmpty.message}")
    @Size(min = 1, max = 45)
    private String lastName;


    @NotEmpty(message = "{constraints.NotEmpty.message}")
    @Size(min = 1, max = 75)
    private String email;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    @Size(min = 1, max = 45)
    private String department;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    @Size(min = 1, max = 10)
    private String mobileCountryCode;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    @Size(min = 1, max = 15)
    private String mobile;

    // @NotEmpty(message = "{constraints.NotEmpty.message}")
    @Size(min = 1, max = 10)
    private String landlineCountryCode;

    @Size(min = 1, max = 15)
    private String landline;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    @Size(min = 4, max = 300)
    private String address;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    @Size(min = 4, max = 45)
    private String company;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    @Size(min = 4, max = 75)
    private String country;


    @NotEmpty(message = "{constraints.NotEmpty.message}")
    @Size(min = 4, max = 75)
    private String city;


    @NotEmpty(message = "{constraints.NotEmpty.message}")
    @Size(min = 1, max = 15)
    private String pincode;

    @Size(max=8)
    private String password;
}