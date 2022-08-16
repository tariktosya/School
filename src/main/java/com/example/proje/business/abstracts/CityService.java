package com.example.proje.business.abstracts;

import com.example.proje.core.entities.dtos.LessonGetDto;
import com.example.proje.core.utilities.results.DataResult;
import com.example.proje.entities.concretes.City;
import com.example.proje.entities.dtos.CityDistrictDto;
import com.example.proje.entities.dtos.CityDto;

import java.util.List;

public interface CityService {
    DataResult<List<CityDto>> findCityName();

    DataResult<List<CityDistrictDto>> findCityAndDistrict();

}
