package com.om.springboot.service.user;


import com.om.springboot.dto.model.user.CountriesDto;
import org.springframework.stereotype.Component;

public interface CountriesService {

    Boolean existsByCountry(String country);

    CountriesDto getCountryByCountry(String country);
}
