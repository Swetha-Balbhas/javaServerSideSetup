package com.om.springboot.dto.mapper.user;


import com.om.springboot.dto.model.user.DemoRequestDto;
import com.om.springboot.dto.model.user.UserProfileDto;
import com.om.springboot.model.user.DemoRequest;
import com.om.springboot.model.user.UserProfile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DemoRequestDtoMapper {
    public static DemoRequestDto toDemoRequestDto(DemoRequest demoRequest) {
        if(null == demoRequest.getStartTime() || null == demoRequest.getEndTime()) return null;

        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm a");
        Date start = Date.from(demoRequest.getStartTime());
        String startTime = formatter.format(start);
        Date end = Date.from(demoRequest.getEndTime());
        String endTime = formatter.format(end);

        return new DemoRequestDto()
                .setRequestId(demoRequest.getRequestId())
                .setDemoId(demoRequest.getDemoId())
                .setEmail(demoRequest.getEmail())
                .setStartTime(startTime)
                .setEndTime(endTime)
                .setInviteesEmail(demoRequest.getInviteesEmail())
                .setCreatedAt(demoRequest.getCreatedAt());
    }

    public static List<DemoRequestDto> toDemoRequestDtoList(List<DemoRequest> list){
            if(null == list || list.isEmpty()) return null;
            List<DemoRequestDto> dtoList = new ArrayList<DemoRequestDto>();
            for(DemoRequest user : list) {
                dtoList.add(toDemoRequestDto(user));
            }
            return dtoList;
        }
    }

