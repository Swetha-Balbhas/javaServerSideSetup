package com.om.springboot.dto.mapper.user;

import com.om.springboot.dto.model.user.CountriesDto;
import com.om.springboot.model.user.Countries;

public class CountriesDtoMapper {
    public static CountriesDto toCountriesDto(Countries country) {
        return new CountriesDto()
                .setId(country.getCountryId())
                .setCountry(country.getCountry())
                ;
    }
}
