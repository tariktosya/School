package com.example.proje.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.example.proje.business.abstracts.LessonService;
import com.example.proje.model.dtos.lesson.LessonGetDto;
import com.example.proje.utilities.results.DataResult;
import com.example.proje.utilities.results.ErrorDataResult;
import com.example.proje.model.entity.Lesson;
import com.example.proje.model.dtos.lesson.LessonDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/lesson")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @PostMapping("/add")
    public ResponseEntity<?> addLesson(@Valid @RequestBody LessonDto LessonDto) {
        return ResponseEntity.ok(lessonService.addLesson(LessonDto));
    }

    @GetMapping("/getAll")
    public DataResult<List<LessonGetDto>> getAllLesson() {
        return lessonService.getAllLesson();
    }


    @GetMapping("/getAllPage")
    public DataResult<List<LessonGetDto>> getAllPage(int pageNo, int pageSize) {
        return lessonService.getAllPage(pageNo, pageSize);
    }

    @GetMapping("/getByPLessonNameContains")
    public DataResult<List<Lesson>> getByLessonNameContains(@RequestParam String lessonName) {
        return lessonService.getByLessonNameContains(lessonName);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions) {
        Map<String, String> validationErrors = new HashMap<String, String>();

        for (FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors, "Doğrulama hataları");
        return errors;
    }
}
