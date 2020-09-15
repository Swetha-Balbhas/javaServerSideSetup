package com.om.springboot.dto.mapper.user;

import com.om.springboot.dto.model.user.CitiesDto;
import com.om.springboot.model.user.Cities;

import java.util.ArrayList;
import java.util.List;

public class CitiesDtoMapper {
    public static CitiesDto toCitiesDto(Cities cities) {
        return new CitiesDto()
                .setId(cities.getCityId())
                .setCity(cities.getCity())
                ;
    }

    public static List<CitiesDto> toCitiesDtoList(List<Cities> cities) {
        if (null != cities) {
            List<CitiesDto> dtoList = new ArrayList<CitiesDto>();
            for (Cities city : cities) {
                dtoList.add(toCitiesDto(city));
            }
            return dtoList;
        } else {
            return null;
        }
    }
}
