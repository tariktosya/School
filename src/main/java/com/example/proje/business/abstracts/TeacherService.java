package com.example.proje.business.abstracts;


import javax.servlet.http.HttpServletResponse;

import com.example.proje.model.dtos.teacher.TeacherGetDto;
import com.example.proje.utilities.results.DataResult;
import com.example.proje.utilities.results.Result;
import com.example.proje.model.entity.Teacher;
import com.example.proje.model.dtos.teacher.TeacherDto;

import java.util.List;

public interface TeacherService {

    DataResult<List<TeacherGetDto>> getAllTeacher();

    DataResult<List<TeacherGetDto>> getAllPage(int pageNo, int pageSize);

    Result addTeacher(TeacherDto teacherDto);

    Result deleteTeacher(int id);

    DataResult<List<Teacher>> getBySchoolNameContains(String schoolName);

    DataResult<List<Teacher>> getByMailContains(String mail);

    Result updatePhoneNo(int id, String phoneNo);

    Result updatePassword(int id, String password);

    Result exportToExcelTeacher(HttpServletResponse response);

    Result exportToPdfTeacher(HttpServletResponse response);
}
