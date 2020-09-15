package com.om.springboot.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestForDemoRequest {


    @NotEmpty(message = "{constraints.NotEmpty.message}")
    @Size(min = 1, max = 75)
    private String email;

    private Date date;

    private String startTime;

    private String endTime;

   // @NotEmpty(message="{constraints.NotEmpty.message}")
    private List<String> inviteesEmail;


}