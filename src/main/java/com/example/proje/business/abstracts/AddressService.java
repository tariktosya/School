package com.example.proje.business.abstracts;

import com.example.proje.core.utilities.results.DataResult;
import com.example.proje.core.utilities.results.Result;
import com.example.proje.entities.concretes.Distrik;
import com.example.proje.entities.dtos.AddressDto;
import com.example.proje.entities.dtos.CityDto;
import com.example.proje.entities.dtos.LessonDto;

import java.util.List;

public interface AddressService {


    List<String> getByCityNameToCityDistrik(String cityName);

    Result addAddress(AddressDto addressDto);
}
