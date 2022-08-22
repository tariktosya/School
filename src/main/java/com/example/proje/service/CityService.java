package com.example.proje.service;

import com.example.proje.utilities.results.DataResult;
import com.example.proje.utilities.results.SuccessDataResult;
import com.example.proje.repository.AddressRepository;
import com.example.proje.repository.CityRepository;
import com.example.proje.model.entity.City;
import com.example.proje.model.dtos.cityDistrict.CityDistrictDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private AddressRepository addressDao;


    private CityDistrictDto convertEntityToDtoCityDistrict(City city) {
        CityDistrictDto newCityDistrictDto = new CityDistrictDto();
        newCityDistrictDto.setCityName(city.getCityName());
        newCityDistrictDto.setDistrictName(addressDao.getByCityDistrik(city.getCityName()));
        return newCityDistrictDto;
    }

    @Cacheable("cityDistrictDto")
    public DataResult<List<CityDistrictDto>> findCityAndDistrict() throws InterruptedException {
        Thread.sleep(2000);
        return new SuccessDataResult<List<CityDistrictDto>>(cityRepository.findAll()
                .stream()
                .map(this::convertEntityToDtoCityDistrict)
                .collect(Collectors.toList()), "Bilgiler listelendi.");
    }

    @CachePut("cityDistrictDto")
    public DataResult<List<CityDistrictDto>> findCityAndDistrictCachePut() throws InterruptedException {
        Thread.sleep(2000);
        return new SuccessDataResult<List<CityDistrictDto>>(cityRepository.findAll()
                .stream()
                .map(this::convertEntityToDtoCityDistrict)
                .collect(Collectors.toList()), "Bilgiler listelendi.");
    }
}
