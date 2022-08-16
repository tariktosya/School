package com.example.proje.business.abstracts;

import com.example.proje.core.utilities.results.DataResult;
import com.example.proje.entities.dtos.CityDistrikDto;
import com.example.proje.entities.dtos.CityDto;

import java.util.List;

public interface CityService {
    DataResult<List<CityDto>> findCityName();

    DataResult<List<CityDistrikDto>> findCityAndDistrict();

}
