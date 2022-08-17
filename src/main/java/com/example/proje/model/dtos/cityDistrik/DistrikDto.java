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
public class DistrikDto {
    private List<String> distrikName;
}
