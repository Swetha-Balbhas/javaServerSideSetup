package com.om.springboot.service.user;

import com.om.springboot.dto.mapper.user.CountriesDtoMapper;
import com.om.springboot.dto.model.user.CountriesDto;
import com.om.springboot.dto.model.user.UserProfileDto;
import com.om.springboot.mappers.user.CountriesMapper;
import com.om.springboot.mappers.user.OtpMapper;
import com.om.springboot.model.user.Countries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class CountriesServiceImpl implements CountriesService {

    @Autowired
    @Qualifier("countriesMapper")
    private CountriesMapper countriesMapper;


    @Override
    public Boolean existsByCountry(String country) {
        Boolean isCountryExists = countriesMapper.findByCountry(country);
        if (null != isCountryExists && isCountryExists) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }


    @Override
    public CountriesDto getCountryByCountry(String country) {
        return CountriesDtoMapper
                .toCountriesDto(countriesMapper.getCountriesByCountry(country));
    }
}







