package com.example.proje.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "student_lesson", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class StudentLesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_lesson_id")
    private int studentLessonId;

    @Column(name = "student_id")
    private int studentId;

    @Column(name = "lesson_id")
    private int lessonId;
}
