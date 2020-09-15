package com.om.springboot.dto.model.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserProfileDto {

 private Long userId;
 private String firstName;
 private String lastName;
 private String email;
 private String department;
 private String mobile;
 private String landline;
 private String company;
 private String address;
 private String country;
 private String city;
 private String pincode;
 private String password;
 private Instant createdAt;

}
