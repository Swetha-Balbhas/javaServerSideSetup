package com.om.springboot.model.user;

import com.om.springboot.model.audit.DateAudit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name ="user_login", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"mobile"
        }),
        @UniqueConstraint(columnNames = {"email"
        })
})
public class AdminProfile extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank
    @Size(max = 45)
    private String name;

    @Size(max = 11)
    private String mobile;

    @NotBlank
    @Size(max = 45)
    private String email;

    @Size(max = 11)
    private Long roleId;
}

