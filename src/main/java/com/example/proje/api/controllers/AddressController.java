package com.example.proje.api.controllers;

import com.example.proje.business.abstracts.AddressService;
import com.example.proje.business.abstracts.CityService;
import com.example.proje.core.utilities.results.DataResult;
import com.example.proje.entities.dtos.AddressDto;
import com.example.proje.entities.dtos.CityDistrictDto;
import com.example.proje.entities.dtos.CityDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/address")
public class AddressController {


    public Map<String, String> citiesMap;
    @Autowired
    private AddressService addressService;

    @Autowired
    private CityService cityService;

    public AddressController() {
    }

    @Cacheable("/")
    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public DataResult<List<CityDistrictDto>> index() throws InterruptedException {
        Thread.sleep(3000);
        return cityService.findCityAndDistrict();
    }

    @Cacheable(value = "loadCiytByDistrik", key = "#cityName")
    @ResponseBody
    @GetMapping(value = "loadCiytByDistrik/{cityName}")
    public List<String> loadCityByDistrik(@PathVariable("cityName") String cityName) throws InterruptedException{
        Thread.sleep(3000);
        System.out.println(addressService.getByCityNameToCityDistrik(cityName));
        return addressService.getByCityNameToCityDistrik(cityName);
    }

    @CachePut(value = "addAddress", key = "#addressDto.addressCityName")
    @PostMapping("/addAddress")
    public ResponseEntity<?> addAddress(@Valid @RequestBody AddressDto addressDto) throws InterruptedException{
        Thread.sleep(3000);
        return ResponseEntity.ok(addressService.addAddress(addressDto));
    }

    @Cacheable(value = "get", key = "#cityName")
    @GetMapping("/get/{cityName}")
    public List<String> getCity(@Valid @RequestParam String cityName) throws InterruptedException{
        Thread.sleep(3000);
        System.out.println(addressService.getByCityNameToCityDistrik(cityName));
        return addressService.getByCityNameToCityDistrik(cityName);
    }
    //ss
    //    @PostMapping("/")
//    public DataResult<List<CityDto>> selectCity(@Valid @RequestParam CityDto cityDto){
//
//        return cityDto.setCityList(cityService.getAllCityName());
//    }



}
