package com.example.proje.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.proje.model.entity.Teacher;

public interface WebMailDao extends JpaRepository<Teacher, String> {

    boolean existsByMail(String mail);
}