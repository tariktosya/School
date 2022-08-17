package com.example.proje.model.dtos.student;

import lombok.AllArgsConstructor;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class StudentDto {

    @NotNull
    @NotBlank
    @Size(min = 11, max = 11)
    private String identificationNo;

    @NotNull
    @NotBlank
    @Size(min = 4, max = 4)
    private String registrationYear;

    @NotNull
    @NotBlank
    @Email
    @Size(min = 3, max = 50)
    private String email;


    @NotNull
    @NotBlank
    private String password;

    private List<Integer> lessonId;

}
