package com.example.proje.entities.dtos;

import com.example.proje.entities.concretes.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.util.List;

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
