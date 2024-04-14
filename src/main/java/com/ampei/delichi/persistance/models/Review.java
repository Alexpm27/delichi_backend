package com.ampei.delichi.persistance.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    private LocalDateTime date;

    @Column(length = 500)
    private String content;

    @Column(nullable = false)
    private Integer score;

    @ManyToOne
    private User user;

    @ManyToOne
    private Restaurant restaurant;

}