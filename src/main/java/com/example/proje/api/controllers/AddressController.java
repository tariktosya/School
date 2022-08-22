package com.example.proje.api.controllers;

import com.example.proje.service.AddressService;
import com.example.proje.service.CityService;
import com.example.proje.service.UserService;
import com.example.proje.utilities.results.DataResult;
import com.example.proje.model.dtos.address.AddressDto;
import com.example.proje.model.dtos.cityDistrict.CityDistrictDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @Autowired
    private CityService cityService;

    //@CachePut("cityDistrik")
    @GetMapping("/getCityAndDistrictCacheAble")
    public DataResult<List<CityDistrictDto>> getCityAndDistrictCacheable() throws InterruptedException {
        return cityService.findCityAndDistrict();
    }

    @GetMapping(value = {"/getCityDistrictCachePut"})
    public DataResult<List<CityDistrictDto>> getWithCachePut() throws InterruptedException {
        return cityService.findCityAndDistrictCachePut();
    }

    @CacheEvict(value = "cityDistrictDto", allEntries = true)
    @GetMapping("/cleanCachewithCacheEvitch")
    public String cleanCachewithCacheEvitch() throws InterruptedException {
        //Thread.sleep(2000);
        return "getWithCachePut";
    }


    @GetMapping(value = "loadCiytByDistrict/{cityName}")
    public List<String> loadCityByDistrict(@PathVariable("cityName") String cityName) throws InterruptedException {
        //Thread.sleep(3000);
        System.out.println(addressService.getByCityNameToCityDistrik(cityName));
        return addressService.getByCityNameToCityDistrik(cityName);
    }

    @PostMapping("/addAddress")
    public ResponseEntity<?> addAddress(@Valid @RequestBody AddressDto addressDto) throws InterruptedException {
        //Thread.sleep(3000);
        return ResponseEntity.ok(addressService.addAddress(addressDto));
    }

    /*@GetMapping("fetchFromCache")
    public Cache fetchFromCache(@RequestParam("id") String id) {
        return addressService.fetchFromCache(id);
    }*/


    //    @PostMapping("/")
//    public DataResult<List<CityDto>> selectCity(@Valid @RequestParam CityDto cityDto){
//
//        return cityDto.setCityList(cityService.getAllCityName());
//    }


}
