package com.om.springboot.service.user;


import com.om.springboot.dto.model.user.DemoRequestDto;
import com.om.springboot.mappers.user.DemoRequestMapper;
import com.om.springboot.model.user.DemoRequest;
import com.om.springboot.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class DemoRequestServiceImpl implements DemoRequestService {

    @Autowired
    @Qualifier("demoRequestMapper")
    private DemoRequestMapper demoRequestMapper;

    @Override
    public Long getMaxDemoId() {
        Long demoId = demoRequestMapper.getMaxDemoId();
        if (null == demoId) {
            return AppConstants.DEFAULT_DEMO_ID;
        } else
            return demoId;
    }

    @Transactional
    @Override
    public Long insertDemoRequest(DemoRequestDto demoRequestDto) {
        Long demoId = this.getMaxDemoId() + 1;
        System.out.println("DemoId ---------------->" + demoId);
        DemoRequest demoRequest = new DemoRequest();
        demoRequest.setEmail(demoRequestDto.getEmail());
        demoRequest.setDemoId(demoId);

        Date appointment = demoRequestDto.getDate();
        String startTime = demoRequestDto.getStartTime();
        String endTime = demoRequestDto.getEndTime();

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh a");

        String appointmentStart = format.format(appointment) + " " + startTime;
        String appointmentEnd = format.format(appointment) + " " + endTime;
        System.out.println("appointmentStart ---------------------------->" + appointmentStart);
        System.out.println("appointmentEnd ---------------------------->" + appointmentEnd);

        Date start = new Date();
        Date end = new Date();
        try {
            start = formatter.parse(appointmentStart);
            System.out.println("......>  value is given by in date......" + start + "&&" + end);
            end = formatter.parse(appointmentEnd);
        } catch (ParseException e) {
            System.out.println("Cannot add the guest request of invalid input start time.");
            return AppConstants.DEFAULT_ERROR_NUMBER;
        }

        demoRequest.setStartTime(start.toInstant());
        demoRequest.setEndTime(end.toInstant());
        demoRequest.setInviteesEmail(demoRequestDto.getInviteesEmail());
        demoRequest.setCreatedAt(demoRequestDto.getCreatedAt());
        Boolean isDemoInserted = demoRequestMapper.insertDemoRequest(demoRequest);
        if (null != isDemoInserted && isDemoInserted) {
            DemoRequest demoDetail = demoRequestMapper.getDemoRequestsByDemoId(demoId);
            if (null == demoDetail) {
                return AppConstants.DEFAULT_ERROR_NUMBER;
            } else {
                Long guestDemoId = demoDetail.getDemoId();
                return guestDemoId;
            }
        } else {
            return AppConstants.DEFAULT_ERROR_NUMBER;
        }
    }

    @Override
    public List<String> getAvailableSlotsForDate(String search) {
        if (null == search || search.isEmpty()) return null;
        search = search.trim() + " 00:00:59";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date searchDate = null;
        try {
            searchDate = format.parse(search);

            System.out.println("Search Date is :" + searchDate.toLocaleString());
        } catch (ParseException e) {
            System.err.println("Invalid search String to get available slots :" + search);
            return null;
        }

        Date uptoDate = new Date(searchDate.getTime() + (1000 * 60 * 60 * 24));
        System.out.println(".............> search date added with one day...........>" + uptoDate);
        System.out.println("Upto date time is : " + format.format(searchDate));


        List<String> startTimeList = new ArrayList<String>();
        int dayStart = 10; //10 AM
        int dayEnd = 20; //8 PM
        List<Integer> daySlots = new ArrayList<Integer>();
        while (dayStart < dayEnd) {
            daySlots.add(dayStart);
            dayStart++;
        }
        List<DemoRequest> demoRequests = demoRequestMapper.getDemoRequestDateRange(searchDate, uptoDate);
        if (null != demoRequests) {
            for (DemoRequest request : demoRequests) {
                LocalDateTime ldtStartTime = LocalDateTime.ofInstant(request.getStartTime(), ZoneId.systemDefault());
                System.out.println("...........>........start time at local date time......." + ldtStartTime);
                daySlots.remove((Integer) ldtStartTime.getHour());
                System.out.println("...........Someone boooked in this slot......." + ldtStartTime.getHour());
            }
        }

        int endHour = 0;
        String endMeridian = " AM";
        String meridian = " AM -- ";
        System.out.println("Dayslots -->" + daySlots);
        if (!daySlots.isEmpty()) {
            for (Integer startHour : daySlots) {
                endHour = startHour + 1;
                if (startHour == 12) {
                    meridian = " PM --";
                } else if (startHour > 12) {
                    meridian = " PM -- ";
                    startHour = startHour - 12;
                }
                if (endHour == 12) {
                    endMeridian = " PM";
                } else if (endHour > 12) {
                    endHour = endHour - 12;
                    endMeridian = " PM";
                }
                startTimeList.add("" + startHour + meridian + endHour + endMeridian);
            }
        }

        return startTimeList;
    }

/*
    @Override
    public List<String> getAvailableSlotsForDate(String search) {
        List<String> startTimeList = new ArrayList<String>();
        int dayStart = 0; //12 AM
        int dayEnd = 24; //24 PM
        List<Integer> daySlots = new ArrayList<Integer>();
        while (dayStart < dayEnd) {
            daySlots.add(dayStart);
            dayStart++;
        }
        int endHour = 0;
        String endMeridian = ":00";
        String meridian = ":00 -- ";
        System.out.println("Dayslots -->" + daySlots);
        if (!daySlots.isEmpty()) {
            for (Integer startHour : daySlots) {
                endHour = startHour + 1;
                if (startHour == 12) {
                    meridian = ":00 -- ";
                } else if (startHour > 24) {
                    meridian = ":00 -- ";
                    startHour = startHour + 12;
                }
                if (endHour == 24) {
                    endMeridian = ":00";
                } else if (endHour > 24) {
                    endHour = endHour + 12;
                    endMeridian = ":00";
                }

                startTimeList.add("" + startHour + meridian + endHour + endMeridian);
            }
        }

        return startTimeList;
    }
*/
}
