package com.example.proje.business.concretes;

import com.example.proje.business.abstracts.CityService;
import com.example.proje.core.utilities.results.DataResult;
import com.example.proje.core.utilities.results.SuccessDataResult;
import com.example.proje.dataAccess.AddressDao;
import com.example.proje.dataAccess.CityDao;
import com.example.proje.entities.concretes.City;
import com.example.proje.entities.dtos.CityDistrictDto;
import com.example.proje.entities.dtos.CityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CityManager implements CityService {

    @Autowired
    private CityDao cityDao;

    @Autowired
    private AddressDao addressDao;

    private CityDto convertEntityToDto(City city) {
        CityDto newCityDto = new CityDto();
        newCityDto.setName(cityDao.findCityName());
        return newCityDto;
    }

    private CityDistrictDto convertEntityToDtoCityDistrict(City city) {
        CityDistrictDto newCityDistrictDto = new CityDistrictDto();
        newCityDistrictDto.setCityName(city.getCityName());
        newCityDistrictDto.setDistrictName(addressDao.getByCityDistrik(city.getCityName()));
        return newCityDistrictDto;
    }


    @Override
    public DataResult<List<CityDto>> findCityName() {
        return new SuccessDataResult<List<CityDto>>(cityDao.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList()), "Bilgiler listelendi.");
    }

    @Override
    public DataResult<List<CityDistrictDto>> findCityAndDistrict() {
        return new SuccessDataResult<List<CityDistrictDto>>(cityDao.findAll()
                .stream()
                .map(this::convertEntityToDtoCityDistrict)
                .collect(Collectors.toList()), "Bilgiler listelendi.");
    }
}
