package com.example.proje.api.controllers;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.example.proje.business.abstracts.TeacherService;
import com.example.proje.model.dtos.teacher.TeacherGetDto;
import com.example.proje.utilities.results.DataResult;
import com.example.proje.utilities.results.ErrorDataResult;
import com.example.proje.model.entity.Teacher;
import com.example.proje.model.dtos.teacher.TeacherDto;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/getAll")
    public DataResult<List<TeacherGetDto>> getAllTeacher() {
        return teacherService.getAllTeacher();
    }

    @GetMapping("/getAllPage")
    public DataResult<List<TeacherGetDto>> getAllPage(int pageNo, int pageSize) {
        return teacherService.getAllPage(pageNo, pageSize);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTeacher(@Valid @RequestBody TeacherDto TeacherDto) {
        return ResponseEntity.ok(teacherService.addTeacher(TeacherDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteTeacher(@RequestParam int id) {
        return ResponseEntity.ok(teacherService.deleteTeacher(id));
    }

    @GetMapping("/getByWebsiteMailContains")
    public DataResult<List<Teacher>> getByWebsiteMailContains(@RequestParam String webMail) {
        return teacherService.getByMailContains(webMail);
    }

    @GetMapping("/getByCompanyNameContains")
    public DataResult<List<Teacher>> getByCompanyNameContains(@RequestParam String companyName) {
        return teacherService.getBySchoolNameContains(companyName);
    }

    @GetMapping("/updatePhoneNo")
    public ResponseEntity<?> updatePhoneNo(@RequestParam int id, @RequestParam String phoneNo) {
        return ResponseEntity.ok(teacherService.updatePhoneNo(id, phoneNo));
    }

    @GetMapping("/updatePassword")
    public ResponseEntity<?> updatePassword(@RequestParam int id, @RequestParam String password) {
        return ResponseEntity.ok(teacherService.updatePassword(id, password));
    }

    @GetMapping("/Excel")
    public ResponseEntity<?> exportToExcelTeacher(HttpServletResponse response) throws IOException {
        return ResponseEntity.ok(teacherService.exportToExcelTeacher(response));
    }

    @GetMapping("/Pdf")
    public ResponseEntity<?> exportToPdfTeacher(HttpServletResponse response) throws IOException {
        return ResponseEntity.ok(teacherService.exportToPdfTeacher(response));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions) {
        Map<String, String> validationErrors = new HashMap<>();

        for (FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors, "Doğrulama hataları");
        return errors;
    }
}