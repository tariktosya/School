package com.example.proje.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.proje.model.dtos.lesson.LessonGetDto;
import com.example.proje.utilities.results.DataResult;
import com.example.proje.utilities.results.ErrorDataResult;
import com.example.proje.utilities.results.Result;
import com.example.proje.utilities.results.SuccessDataResult;
import com.example.proje.utilities.results.SuccessResult;
import com.example.proje.repository.LessonRepository;
import com.example.proje.model.entity.Lesson;
import com.example.proje.model.dtos.lesson.LessonDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;


    private LessonGetDto convertEntityToDto(Lesson lesson) {
        LessonGetDto newlessonGetDto = new LessonGetDto();
        newlessonGetDto.setLessonId(lesson.getLessonId());
        newlessonGetDto.setLessonName(lesson.getLessonName());
        newlessonGetDto.setSectionName(lesson.getSectionName());
        return newlessonGetDto;
    }

    public DataResult<List<LessonGetDto>> getAllLesson() {
        return new SuccessDataResult<List<LessonGetDto>>(lessonRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList()), "Bilgiler listelendi.");
    }

    public DataResult<List<LessonGetDto>> getAllPage(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of((pageNo - 1), pageSize);

        if (lessonRepository.findAll(pageable).getContent().size() == 0) {
            return new ErrorDataResult<List<LessonGetDto>>("Kullanıcı bulunamadı.");
        } else {
            return new SuccessDataResult<List<LessonGetDto>>(lessonRepository.findAll(pageable).getContent()
                    .stream()
                    .map(this::convertEntityToDto)
                    .collect(Collectors.toList()), "Bilgiler sayfa numarası ve sırasına göre getiriliyor.");
        }
    }

    public DataResult<List<Lesson>> getByLessonNameContains(String lessonName) {
        if (lessonRepository.existsBySectionName(lessonName)) { //hata
            return new ErrorDataResult<List<Lesson>>("Ders bulunamadı.");
        } else {
            return new SuccessDataResult<List<Lesson>>(lessonRepository.getByLessonNameContains(lessonName), "Data listelendi.");
        }
    }

    public Result addLesson(LessonDto lessonDto) {
        Lesson newLesson = new Lesson();

        newLesson.setLessonName(lessonDto.getLessonName());
        newLesson.setSectionName(lessonDto.getSectionName());
        ;
        this.lessonRepository.save(newLesson);
        return new SuccessResult("Ders eklendi");
    }

    public DataResult<List<Lesson>> getAllByLessonIdInTarik(List<Integer> lessonId) {
        return new SuccessDataResult<List<Lesson>>(this.lessonRepository.getAllByLessonIdInTarik(lessonId));
    }

  /* @Override
    public DataResult<List<String>> getByStudentIdLessons(List<Integer> lessonId) {
        return null;
    }*/
}
