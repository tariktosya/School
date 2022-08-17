package com.example.proje.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.proje.model.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    boolean existsById(int id);

    boolean existsByIdentificationNoContains(String identificationNo);

    Student findById(int id);

    List<Student> getByIdentificationNoContains(String identificationNo);

    /*@Query(value = "SELECT student_lesson.lesson_id FROM student INNER JOIN student_lesson " +
            "ON student.student_id = student_lesson.student_id" +
            "WHERE student.student_id in :studentId",nativeQuery = true)*/
    @Query(value = "SELECT l.lesson_id FROM student as s inner join student_lesson as l on s.student_id = l.student_id" +
            " where s.student_id = :studentId", nativeQuery = true)
    List<Integer> getByStudentLessons(@Param("studentId") int studentId);


    //    @Query("Select New com.example.proje.entities.dtos.lesson.GetLessonId" +
//            " (l.lessonId) " +
//            "FROM Student s " +
//            "Inner join s.lessons as l " +
//            "Where s.studentId = ?1")



   /* @Query(value = "Select s.student_id AS id, s.identification_no AS identificationNo, s.registration_year AS registrationYear,s.e_mail AS email," +
            "s.lessons.lesson_name AS lessonName, s.lessons.section_name AS sectionName" +
            "From student s",
            nativeQuery = true)
    List<StudentGetWithLessonDto> getByStudentWithLessonDetails();*/

   /* @Query(value = "Select #{#com.example.proje.entities.dtos.student.StudentGetDto}"+
            "l.lesson_id as lessonId, l.lesson_name as lessonName, l.section_name as sectionName" +
            " From Student s" +
            "inner join s.lessons l", nativeQuery = true)
    List<StudentGetDto> findByStudentGetDto();*/


    /*@Query(value = "Select com.example.proje.entities.dtos.student.StudentGetDto"
            + "(s.id, s.identificationNo, s.registrationYear, s.email, s.password, s.lessons)"
            + " From Student s", nativeQuery = true)
    List<StudentGetDto> findByStudentGetDto();*/


    /*@Query("Select new com.example.proje.core.entities.dtos.StudentWithLessonDto"
            + "(s.identificationNo, l.lessonName)"
            + " From Student s"
            + " Inner Join s.lessons l")
    List<StudentWithLessonDto> getAllStudentWithLessonDto();*/
}
