package com.example.proje.dataAccess;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.proje.model.entity.Student;

public interface IdentificationNoEmailDao extends JpaRepository<Student, String> {

    boolean existsByIdentificationNo(String identificationNo);

    boolean existsByEmail(String email);
}