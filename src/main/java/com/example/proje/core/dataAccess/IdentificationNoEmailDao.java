package com.example.proje.core.dataAccess;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.proje.entities.concretes.Student;

public interface IdentificationNoEmailDao extends JpaRepository<Student, String> {

    boolean existsByIdentificationNo(String identificationNo);

    boolean existsByEmail(String email);
}