package com.example.proje.business.abstracts;


import java.util.List;


import com.example.proje.model.dtos.lesson.LessonGetDto;
import com.example.proje.utilities.results.DataResult;
import com.example.proje.utilities.results.Result;
import com.example.proje.model.entity.Lesson;
import com.example.proje.model.dtos.lesson.LessonDto;

public interface LessonService {

    DataResult<List<LessonGetDto>> getAllLesson();

    DataResult<List<LessonGetDto>> getAllPage(int pageNo, int pageSize);

    DataResult<List<Lesson>> getByLessonNameContains(String firstName);

    Result addLesson(LessonDto lessonDto);

    DataResult<List<Lesson>> getAllByLessonIdInTarik(List<Integer> lessonId);

    //DataResult<List<String>> getByStudentIdLessons(List<Integer> lessonId);

}
