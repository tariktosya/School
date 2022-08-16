package com.example.proje.entities.dtos;

import com.example.proje.entities.concretes.City;
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
public class CityDto {
    private List<String> name;
}
