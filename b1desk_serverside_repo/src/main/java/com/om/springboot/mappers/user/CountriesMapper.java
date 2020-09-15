package com.om.springboot.mappers.user;

import com.om.springboot.model.user.Countries;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface CountriesMapper {

    Countries getCountryByCountryId(Long countryId);
    Boolean insertCountry(Countries country);

    Boolean findByCountry(String country);
    Countries getCountriesByCountry(String country);

}