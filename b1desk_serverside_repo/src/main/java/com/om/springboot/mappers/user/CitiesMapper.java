package com.om.springboot.mappers.user;

import com.om.springboot.model.user.Cities;
import com.om.springboot.model.user.Countries;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CitiesMapper {
  List<Cities>  getCitiesByCountryId(Long countryId);
}