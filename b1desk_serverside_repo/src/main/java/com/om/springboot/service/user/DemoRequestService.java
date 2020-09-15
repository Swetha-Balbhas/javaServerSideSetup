package com.om.springboot.service.user;

import com.om.springboot.dto.model.user.DemoRequestDto;

import java.util.List;

public interface DemoRequestService {
    List<String> getAvailableSlotsForDate(String search);
    Long getMaxDemoId();
    Long insertDemoRequest(DemoRequestDto demoRequestDto);
}
