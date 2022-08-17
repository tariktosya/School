package com.example.proje.model.dtos.teacher;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto {

    @NotNull
    @NotBlank
    private String schoolName;

    @NotNull
    @NotBlank
    @Email
    @Size(min = 3, max = 50)
    private String mail;

    @NotNull
    @NotBlank
    @Size(min = 11, max = 11)
    @Pattern(regexp = "^\\d{11}$")
    private String phoneNo;

    @NotNull
    @NotBlank
    private String password;

    @NotNull
    @NotBlank
    private String lessonName;

    @NotNull
    @NotBlank
    private String sectionName;
}
