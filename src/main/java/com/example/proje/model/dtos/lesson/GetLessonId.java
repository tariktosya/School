package com.example.proje.model.dtos.lesson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetLessonId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lessonId;
}