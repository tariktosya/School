package com.example.proje.model.dtos.cityDistrict;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DistrictDto {
    private List<String> distrikName;
}
