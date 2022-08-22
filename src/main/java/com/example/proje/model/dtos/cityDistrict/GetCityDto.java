package com.example.proje.model.dtos.cityDistrict;

import com.example.proje.model.entity.City;
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
