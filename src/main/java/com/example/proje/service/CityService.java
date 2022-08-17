package com.example.proje.service;

import com.example.proje.utilities.results.DataResult;
import com.example.proje.utilities.results.SuccessDataResult;
import com.example.proje.repository.AddressRepository;
import com.example.proje.repository.CityRepository;
import com.example.proje.model.entity.City;
import com.example.proje.model.dtos.cityDistrik.CityDistrikDto;
import com.example.proje.model.dtos.cityDistrik.CityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private AddressRepository addressDao;

    private CityDto convertEntityToDto(City city) {
        CityDto newCityDto = new CityDto();
        newCityDto.setName(cityRepository.findCityName());
        return newCityDto;
    }

    private CityDistrikDto convertEntityToDtoCityDistrict(City city) {
        CityDistrikDto newCityDistrictDto = new CityDistrikDto();
        newCityDistrictDto.setCityName(city.getCityName());
        newCityDistrictDto.setDistrictName(addressDao.getByCityDistrik(city.getCityName()));
        return newCityDistrictDto;
    }


    public DataResult<List<CityDto>> findCityName() {
        return new SuccessDataResult<List<CityDto>>(cityRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList()), "Bilgiler listelendi.");
    }

    public DataResult<List<CityDistrikDto>> findCityAndDistrict() {
        return new SuccessDataResult<List<CityDistrikDto>>(cityRepository.findAll()
                .stream()
                .map(this::convertEntityToDtoCityDistrict)
                .collect(Collectors.toList()), "Bilgiler listelendi.");
    }
}
