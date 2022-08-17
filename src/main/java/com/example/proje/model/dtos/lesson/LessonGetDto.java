package com.example.proje.model.dtos.lesson;

import lombok.AllArgsConstructor;


import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonGetDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lessonId;

    @NotNull
    @NotBlank
    private String lessonName;

    @NotNull
    @NotBlank
    private String sectionName;

    
    /*@NotNull
    @NotBlank
    private String identificationNo;*/

}
