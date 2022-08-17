package com.example.proje.repository;

import com.example.proje.model.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

//    @Query(value = "SELECT city_id FROM city where city_name = :cityName", nativeQuery = true)
//    int getByCityNameToId(@Param("cityName") String cityName);

    @Query(value = "SELECT city_name FROM city", nativeQuery = true)
    List<String> findCityName();



}
