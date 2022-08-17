package com.example.proje.business.abstracts;


import javax.servlet.http.HttpServletResponse;

import com.example.proje.model.dtos.student.StudentGetDto;
import com.example.proje.model.dtos.student.StudentGetWithLessonDto;
import com.example.proje.utilities.results.DataResult;
import com.example.proje.utilities.results.Result;
import com.example.proje.model.entity.Student;
import com.example.proje.model.dtos.student.StudentDto;

import java.util.List;

public interface StudentService {

    DataResult<List<StudentGetDto>> getAllStudent();

    DataResult<List<StudentGetDto>> getAllPage(int pageNo, int pageSize);

    Result addStudent(StudentDto studentDto);

    Result deleteStudent(int id);

    Result updatePassword(int id, String password);

    DataResult<List<Student>> getByIdentificationNoContains(String identificationNo);

    DataResult<List<StudentGetWithLessonDto>> getByStudentLessons();

    Result exportToExcelStudent(HttpServletResponse response);

    Result exportToPdfStudent(HttpServletResponse response);

}
