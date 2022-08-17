package com.example.proje.business.abstracts;

import com.example.proje.utilities.results.DataResult;
import com.example.proje.model.dtos.cityDistrik.CityDistrikDto;
import com.example.proje.model.dtos.cityDistrik.CityDto;

import java.util.List;

public interface CityService {
    DataResult<List<CityDto>> findCityName();

    DataResult<List<CityDistrikDto>> findCityAndDistrict();

}
