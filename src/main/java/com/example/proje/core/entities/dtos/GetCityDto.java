package com.example.proje.core.entities.dtos;

import com.example.proje.entities.concretes.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCityDto {
    private List<City> cityList;

}
