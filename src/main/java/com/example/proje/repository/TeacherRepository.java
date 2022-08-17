package com.example.proje.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.proje.model.entity.Teacher;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    boolean existsById(int id);

    boolean existsByPhoneNo(String phoneNumber);

    boolean existsBySchoolName(String schoolName);

    boolean existsByMail(String mail);

    Teacher findById(int id);

    List<Teacher> getBySchoolNameContains(String schoolName);

    List<Teacher> getByMailContains(String webMail);

}
