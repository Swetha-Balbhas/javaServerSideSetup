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
@Table(name = "cities", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "city"
        })
})
public class Cities implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cityId;

    @NotBlank
    @Size(max = 75)
    private String city;

    @NotBlank
    @Size(max = 11)
    private Long countryId;

    @NotBlank
    @Size(max = 1)
    private Integer status;
}

