package com.om.springboot.service.user;


import com.om.springboot.dto.mapper.user.CitiesDtoMapper;
import com.om.springboot.dto.model.user.CitiesDto;
import com.om.springboot.mappers.user.CitiesMapper;
import com.om.springboot.mappers.user.OtpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CitiesServiceImpl implements CitiesService {

    @Autowired
    @Qualifier("citiesMapper")
    private CitiesMapper citiesMapper;


    @Override
    public List<CitiesDto> getCities(Long countryId) {
        return CitiesDtoMapper.toCitiesDtoList(citiesMapper.getCitiesByCountryId(countryId));
    }

}
