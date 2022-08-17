package com.example.proje.business.abstracts;

import com.example.proje.utilities.results.Result;
import com.example.proje.model.dtos.address.AddressDto;

import java.util.List;

public interface AddressService {


    List<String> getByCityNameToCityDistrik(String cityName);

    Result addAddress(AddressDto addressDto);
}
