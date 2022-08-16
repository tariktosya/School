package com.example.proje.api.controllers;

import com.example.proje.core.entities.dtos.StudentGetWithLessonDto;
import com.example.proje.entities.concretes.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.example.proje.business.abstracts.StudentService;
import com.example.proje.core.entities.dtos.StudentGetDto;
import com.example.proje.core.utilities.results.DataResult;
import com.example.proje.core.utilities.results.ErrorDataResult;
import com.example.proje.entities.concretes.Student;
import com.example.proje.entities.dtos.StudentDto;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/getAll")
    public DataResult<List<StudentGetDto>> getAllStudent() {
        return studentService.getAllStudent();
    }


    @GetMapping("/getAllByLesson")
    public DataResult<List<StudentGetWithLessonDto>> getByStudentLessons() {
        return studentService.getByStudentLessons();
    }


    @GetMapping("/getAllPage")
    public DataResult<List<StudentGetDto>> getAllPage(int pageNo, int pageSize) {
        return studentService.getAllPage(pageNo, pageSize);
    }


    @PostMapping("/add")
    public ResponseEntity<?> addStudent(@Valid @RequestBody StudentDto StudentDto) {
        return ResponseEntity.ok(studentService.addStudent(StudentDto));
    }


    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteStudent(@RequestParam int id) {
        return ResponseEntity.ok(studentService.deleteStudent(id));
    }

    @GetMapping("/updatePassword")
    public ResponseEntity<?> updatePassword(@RequestParam int id, @RequestParam String password) {
        return ResponseEntity.ok(studentService.updatePassword(id, password));
    }

    @GetMapping("/getByIdentificationNoContains")
    public DataResult<List<Student>> getByIdentificationNoContains(@RequestParam String identificationNo) {
        return studentService.getByIdentificationNoContains(identificationNo);
    }

    @GetMapping("/Excel")
    public ResponseEntity<?> exportToExcelStudent(HttpServletResponse response) throws IOException {
        return ResponseEntity.ok(studentService.exportToExcelStudent(response));
    }

    @GetMapping("/Pdf")
    public ResponseEntity<?> exportToPdfStudent(HttpServletResponse response) throws IOException {
        return ResponseEntity.ok(studentService.exportToPdfStudent(response));
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