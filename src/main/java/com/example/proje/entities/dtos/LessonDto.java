package com.example.proje.entities.dtos;

import lombok.AllArgsConstructor;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LessonDto {

    @NotNull
    @NotBlank
    private String lessonName;

    @NotNull
    @NotBlank
    private String sectionName;

}
