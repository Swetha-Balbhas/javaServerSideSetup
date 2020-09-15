package com.om.springboot.model.user;

import com.om.springboot.model.audit.DateAudit;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "user_authentication", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "authenticationId"
        })
})
public class UserAuthentication extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authenticationId;

    @NotBlank
    @Size(max = 11)
    private Long id;

    @NotBlank
    @Size(max = 2)
    private Integer retryLoginAttempts;

    @Size(max = 20)
    private Integer status;

    @NotBlank
    @Size(max = 200)
    private String password;

    @CreatedDate
    private Instant loginAt;

    @CreatedDate
    private Instant logoutAt;
}