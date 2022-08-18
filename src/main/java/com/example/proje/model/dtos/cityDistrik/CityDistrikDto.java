package com.example.proje.model.dtos.cityDistrik;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CityDistrikDto implements Serializable {
    private static final long serialVersionUID = 7156526077883281623L;
    private String cityName;
    private List<String> districtName;
}
