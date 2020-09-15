package com.om.springboot.mappers.user;

import com.om.springboot.model.user.DemoRequest;
import com.om.springboot.model.user.UserProfile;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface DemoRequestMapper {

   Long getMaxDemoId();

    Boolean insertDemoRequest(DemoRequest demoRequest);

   List<DemoRequest> getDemoRequestDateRange(Date startTime, Date endTime);

    DemoRequest getDemoRequestsByDemoId(Long demoId);
}
