package com.example.proje.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.proje.model.entity.Teacher;

public interface WebMailRepository extends JpaRepository<Teacher, String> {

    boolean existsByMail(String mail);
}