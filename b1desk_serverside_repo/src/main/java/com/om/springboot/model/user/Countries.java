package com.om.springboot.model.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;



@Getter
@Setter
@Entity
@Table(name = "Countries", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "countryId"
        })
})

public class Countries implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long countryId;

    @NotBlank
    @Size(max = 75)
    private String country;

    @NotBlank
    @Size(max = 1)
    private Integer status;
}
