package com.example.proje.core.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.proje.entities.concretes.Teacher;

public interface WebMailDao extends JpaRepository<Teacher, String> {

    boolean existsByMail(String mail);
}