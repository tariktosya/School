package com.example.proje.core.entities.dtos;

import lombok.AllArgsConstructor;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.swing.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.collections4.Get;

import com.example.proje.entities.concretes.Lesson;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentGetDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;

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

    @ManyToMany
    @NotNull
    private List<Lesson> lessons;

}
