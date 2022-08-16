package com.example.proje.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CityDistrictDto {

    private String cityName;
    private List<String> districtName;
}
