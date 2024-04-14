package com.ampei.delichi.persistance.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    private LocalDateTime date;

    @Column(nullable = false)
    private Integer people;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    private Restaurant restaurant;

    @ManyToOne
    private User user;

}