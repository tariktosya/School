package com.example.proje.model.dtos.cityDistrik;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CityDistrikDto {

    private String cityName;
    private List<String> districtName;
}
