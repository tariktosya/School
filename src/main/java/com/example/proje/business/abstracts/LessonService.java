package com.example.proje.business.abstracts;


import java.util.List;


import com.example.proje.core.entities.dtos.LessonGetDto;
import com.example.proje.core.utilities.results.DataResult;
import com.example.proje.core.utilities.results.Result;
import com.example.proje.entities.concretes.Lesson;
import com.example.proje.entities.dtos.LessonDto;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.crypto.Data;

public interface LessonService {

    DataResult<List<LessonGetDto>> getAllLesson();

    DataResult<List<LessonGetDto>> getAllPage(int pageNo, int pageSize);

    DataResult<List<Lesson>> getByLessonNameContains(String firstName);

    Result addLesson(LessonDto lessonDto);

    DataResult<List<Lesson>> getAllByLessonIdInTarik(List<Integer> lessonId);

    //DataResult<List<String>> getByStudentIdLessons(List<Integer> lessonId);

}
