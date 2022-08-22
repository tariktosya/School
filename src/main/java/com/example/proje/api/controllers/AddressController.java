package com.example.proje.api.controllers;

import com.example.proje.service.AddressService;
import com.example.proje.service.CityService;
import com.example.proje.utilities.results.DataResult;
import com.example.proje.model.dtos.address.AddressDto;
import com.example.proje.model.dtos.cityDistrik.CityDistrikDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.ResponseEntity;
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

    @CachePut("cityDistrik")
    @GetMapping(value = {"/getCityDistrikCachePut"})
    public DataResult<List<CityDistrikDto>> getWithCachePut() throws InterruptedException {
        Thread.sleep(2000);
        return cityService.findCityAndDistrict();
    }

    @GetMapping(value = {"/getCityDistrikDto"})
    public DataResult<List<CityDistrikDto>> getWithCacheable() throws InterruptedException {
        Thread.sleep(2000);
        return cityService.findCityAndDistrict();
    }

    @CacheEvict(value = "cityDistrik", allEntries = true)
    @GetMapping("/cleanCachewithCacheEvitch")
    public String cleanCachewithCacheEvitch() throws InterruptedException {
        //Thread.sleep(2000);
        return "getWithCachePut";
    }

    //@Cacheable(value = "loadCiytByDistrik", key = "#cityName")
    @GetMapping(value = "loadCiytByDistrik/{cityName}")
    public List<String> loadCityByDistrik(@PathVariable("cityName") String cityName) throws InterruptedException {
        //Thread.sleep(3000);
        System.out.println(addressService.getByCityNameToCityDistrik(cityName));
        return addressService.getByCityNameToCityDistrik(cityName);
    }

    //@CachePut(value = "addAddress", key = "#addressDto.addressCityName")
    @PostMapping("/addAddress")
    public ResponseEntity<?> addAddress(@Valid @RequestBody AddressDto addressDto) throws InterruptedException {
        //Thread.sleep(3000);
        return ResponseEntity.ok(addressService.addAddress(addressDto));
    }

    @GetMapping("fetchFromCache")
    public Cache fetchFromCache(@RequestParam("id") String id) {
        return addressService.fetchFromCache(id);
    }


    @GetMapping("/getAllCity")
    public void getAllCityModel() throws InterruptedException {
        cityService.getAllCity();
    }

    //    @PostMapping("/")
//    public DataResult<List<CityDto>> selectCity(@Valid @RequestParam CityDto cityDto){
//
//        return cityDto.setCityList(cityService.getAllCityName());
//    }


}
