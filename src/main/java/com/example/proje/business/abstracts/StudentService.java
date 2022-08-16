package com.example.proje.business.abstracts;


import javax.servlet.http.HttpServletResponse;

import com.example.proje.core.entities.dtos.StudentGetDto;
import com.example.proje.core.entities.dtos.StudentGetWithLessonDto;
import com.example.proje.core.utilities.results.DataResult;
import com.example.proje.core.utilities.results.Result;
import com.example.proje.entities.concretes.Student;
import com.example.proje.entities.dtos.StudentDto;
import io.swagger.models.auth.In;

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
