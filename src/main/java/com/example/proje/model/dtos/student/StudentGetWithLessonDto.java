package com.example.proje.model.dtos.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentGetWithLessonDto {
    private int id;
    private String identificationNo;
    private String registrationYear;
    private String email;
    private List<String> lessonName;
    private List<String> sectionName;
}
