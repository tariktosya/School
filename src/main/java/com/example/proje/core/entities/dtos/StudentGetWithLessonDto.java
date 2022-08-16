package com.example.proje.core.entities.dtos;

import com.example.proje.entities.concretes.Lesson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
