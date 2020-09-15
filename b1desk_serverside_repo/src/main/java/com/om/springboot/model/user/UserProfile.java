package com.om.springboot.model.user;

import com.om.springboot.model.audit.DateAudit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "user_profile", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"mobile"
        })
})
public class UserProfile extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank
    @Size(max = 45)
    private String firstName;

    @NotBlank
    @Size(max = 45)
    private String lastName;

    @NotBlank
    @Size(max = 75)
    private String email;

    @NotBlank
    @Size(max = 75)
    private String department;

    @NotBlank
    @Size(max = 15)
    private String mobile;

    @Size(max = 15)
    private String landline;


    @NotBlank
    @Size(max = 45)
    private String company;

    @NotBlank
    @Size(max = 300)
    private String address;

    @NotBlank
    @Size(max = 75)
    private String country;

    @NotBlank
    @Size(max = 45)
    private String city;

    @NotBlank
    @Size(max = 15)
    private String pincode;

    @Size(max = 8)
    private String password;

}
