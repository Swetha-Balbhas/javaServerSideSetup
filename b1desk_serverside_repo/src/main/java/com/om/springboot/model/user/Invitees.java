package com.om.springboot.model.user;

import com.om.springboot.model.audit.DateAudit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "invitees", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"
        })
})
public class Invitees extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inviteeId;

    @NotNull
    @Size(max = 11)
    private Long demoId;

    @NotEmpty
    @Size(max= 45)
    private String email;

}
