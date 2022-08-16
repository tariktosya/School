package com.example.proje.entities.concretes;

import lombok.AllArgsConstructor;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "teachers")
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private int id;

    @Column(name = "school_name")
    private String schoolName;

    @Column(name = "mail")
    private String mail;

    @Column(name = "phone_no")
    private String phoneNo;

    @Column(name = "password")
    private String password;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;
}