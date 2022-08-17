package com.example.proje.model.dtos.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddressDto {
    private int addressId;
    private String addressCityName;
    private String addressDistrikName;
    private String addressDescription;
    private int studentIds;
}
