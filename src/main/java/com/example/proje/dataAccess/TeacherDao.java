package com.example.proje.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.proje.entities.concretes.Teacher;

import java.util.List;

public interface TeacherDao extends JpaRepository<Teacher, Integer> {

    boolean existsById(int id);

    boolean existsByPhoneNo(String phoneNumber);

    boolean existsBySchoolName(String schoolName);

    boolean existsByMail(String mail);

    Teacher findById(int id);

    List<Teacher> getBySchoolNameContains(String schoolName);

    List<Teacher> getByMailContains(String webMail);

}
