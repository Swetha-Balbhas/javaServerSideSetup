package com.om.springboot.service.user;

import com.om.springboot.dto.model.user.CitiesDto;

import java.util.List;

public interface CitiesService {
    List<CitiesDto> getCities(Long countryId);
}
