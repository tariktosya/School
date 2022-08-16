package com.example.proje.dataAccess;

import com.example.proje.entities.concretes.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityDao extends JpaRepository<City, Integer> {

//    @Query(value = "SELECT city_id FROM city where city_name = :cityName", nativeQuery = true)
//    int getByCityNameToId(@Param("cityName") String cityName);

    @Query(value = "SELECT city_name FROM city", nativeQuery = true)
    List<String> findCityName();



}
