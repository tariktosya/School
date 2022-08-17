package com.example.proje.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id",nullable=true, updatable=true)
    private int addressId;

    @Column(name = "address_city_name")
    private String addressCityName;

    @Column(name = "address_distrik_name")
    private String addressDistrikName;

    @Column(name = "address_description")
    private String addressDescription;
    @ManyToOne
    @JoinColumn(name="student_id", nullable=false)
    private Student studentId;
}
