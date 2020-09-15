package com.om.springboot.controller.ui;

import com.om.springboot.controller.request.RequestForDemoRequest;
import com.om.springboot.dto.model.user.DemoRequestDto;
import com.om.springboot.dto.response.ApiResponse;
import com.om.springboot.dto.response.user.TimeSlotsAvailabilityResponse;
import com.om.springboot.service.user.DemoRequestService;
import com.om.springboot.util.ErrorConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/b1desk")
public class RequestDemoController {

    @Autowired
    DemoRequestService requestDemoService;


    @CrossOrigin
    @GetMapping("/postLogin/getTimeForSlots/{date}")
    public ResponseEntity<?> getAvailableTimeSlots(@PathVariable String date) {
        List<String> availableSlotsForDate = requestDemoService.getAvailableSlotsForDate(date);
        if (null == availableSlotsForDate || availableSlotsForDate.isEmpty()) {
            return new ResponseEntity(new ApiResponse(false, ErrorConstants.E111.name()),
                    HttpStatus.OK);
        } else {
            TimeSlotsAvailabilityResponse timeSlotsAvailabilityResponse =
                    new TimeSlotsAvailabilityResponse(Boolean.TRUE, "")
                            .setSlots(availableSlotsForDate);
            return new ResponseEntity(timeSlotsAvailabilityResponse, HttpStatus.OK);
        }
    }

    @CrossOrigin
    @PostMapping("/postLogin/demoRequest")
    public ResponseEntity<?> insertDemoRequest(@Valid @RequestBody RequestForDemoRequest requestForDemoRequest) {
        DemoRequestDto demoRequestDto = new DemoRequestDto()
                .setEmail(requestForDemoRequest.getEmail())
                .setDate(requestForDemoRequest.getDate())
                .setStartTime(requestForDemoRequest.getStartTime())
                .setEndTime(requestForDemoRequest.getEndTime());
        if (null != requestForDemoRequest.getInviteesEmail()) {
            demoRequestDto.setInviteesEmail(String.join(" , ", requestForDemoRequest.getInviteesEmail()));
        }
        demoRequestDto.setCreatedAt(Instant.now());
        Long demoId = requestDemoService.insertDemoRequest(demoRequestDto);
        if (null != demoId) {
            return new ResponseEntity(new ApiResponse(true, "" + demoId), HttpStatus.OK);
        } else {
            return new ResponseEntity(new ApiResponse(false, ErrorConstants.E110.name()), HttpStatus.OK);
        }
    }

}
