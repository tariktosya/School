package com.example.proje.entities.concretes;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "lesson")
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_id")
    private int lessonId;

    @Column(name = "lesson_name")
    private String lessonName;

    @Column(name = "section_name")
    private String sectionName;

    @OneToOne(mappedBy = "lesson")
    @JsonIgnore
    private Teacher teacher;
    
    /*@ManyToMany()
    @JsonIgnore
    private List<Student> students;*/

}