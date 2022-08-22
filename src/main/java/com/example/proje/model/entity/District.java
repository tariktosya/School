package com.example.proje.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "distrik")
@AllArgsConstructor
@NoArgsConstructor
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "distrik_id")
    private int distrikId;

    @Column(name = "distrik_name")
    private String distrikName;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;
}
