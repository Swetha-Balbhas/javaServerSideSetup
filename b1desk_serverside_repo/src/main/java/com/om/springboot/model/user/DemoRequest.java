package com.om.springboot.model.user;

import com.om.springboot.model.audit.DateAudit;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "demo_request", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"
        })
})

public class DemoRequest extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;

    @NotNull
    @Size(max = 11)
    private Long demoId;

    @NotEmpty
    @Size(max= 45)
    private String email;

    @NotNull
    private Instant startTime;

    @NotNull
    private Instant endTime;

    //@NotEmpty
    @Size(max= 500)
    private String inviteesEmail;

}
