package com.example.proje.dataAccess;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.proje.model.entity.Lesson;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LessonDao extends JpaRepository<Lesson, Integer> {

    List<Lesson> getByLessonNameContains(String lessonName);

    boolean existsBySectionName(String sectionName);
    
    @Query(value = "Select * From lesson where lesson_id in :lessonId", nativeQuery = true)
    List<Lesson> getAllByLessonIdInTarik(@Param("lessonId") List<Integer> lessonId);

    @Query(value = "SELECT lesson_name FROM lesson l Where l.lesson_id in :lessonId",nativeQuery = true)
    List<String> getByStudentIdLessonName(List<Integer> lessonId);

    @Query(value = "SELECT section_name FROM lesson l Where l.lesson_id in :lessonId",nativeQuery = true)
    List<String> getByStudentIdSectionName(List<Integer> lessonId);
    //List<Lesson> findByLessonIdIn(List<Integer> lessonId);
    
    
}
