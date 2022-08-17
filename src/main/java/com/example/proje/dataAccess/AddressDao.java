package com.example.proje.dataAccess;

import com.example.proje.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressDao extends JpaRepository<Address,Integer> {



    @Query(value = "SELECT d.distrik_name FROM city as c inner join distrik as d on c.city_id = d.city_id where c.city_name = :cityName", nativeQuery = true)
    List<String> getByCityDistrik(@Param("cityName") String cityName);
}
