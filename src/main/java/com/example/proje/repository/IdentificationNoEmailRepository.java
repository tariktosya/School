package com.example.proje.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.proje.model.entity.Student;

public interface IdentificationNoEmailRepository extends JpaRepository<Student, String> {

    boolean existsByIdentificationNo(String identificationNo);

    boolean existsByEmail(String email);
}