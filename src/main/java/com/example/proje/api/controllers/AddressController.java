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

    @CachePut("cityDistrik")
    @RequestMapping(value = { "/getCityDistrikCachePut" }, method = RequestMethod.GET)
    public DataResult<List<CityDistrictDto>> getWithCachePut() throws InterruptedException {
        Thread.sleep(3000);
        return cityService.findCityAndDistrict();
    }

    //@Cacheable(value = "loadCiytByDistrik", key = "#cityName")
    @GetMapping(value = "loadCiytByDistrik/{cityName}")
    public List<String> loadCityByDistrik(@PathVariable("cityName") String cityName) throws InterruptedException{
        //Thread.sleep(3000);
        System.out.println(addressService.getByCityNameToCityDistrik(cityName));
        return addressService.getByCityNameToCityDistrik(cityName);
    }

    //@CachePut(value = "addAddress", key = "#addressDto.addressCityName")
    @PostMapping("/addAddress")
    public ResponseEntity<?> addAddress(@Valid @RequestBody AddressDto addressDto) throws InterruptedException{
        //Thread.sleep(3000);
        return ResponseEntity.ok(addressService.addAddress(addressDto));
    }
    @Cacheable("cityDistrik")
    @GetMapping(value = { "/getCityDistrikCacheable" })
    public DataResult<List<CityDistrictDto>> getWithCacheable() throws InterruptedException {
        Thread.sleep(2000);
        return cityService.findCityAndDistrict();
    }

    @CacheEvict("cityDistrik")
    @GetMapping("/cleanCachewithCacheEvitch")
    public String cleanCachewithCacheEvitch() throws InterruptedException{
        //Thread.sleep(2000);
        return "getWithCachePut";
    }

    //    @PostMapping("/")
//    public DataResult<List<CityDto>> selectCity(@Valid @RequestParam CityDto cityDto){
//
//        return cityDto.setCityList(cityService.getAllCityName());
//    }



}
