package com.ampei.delichi.persistance.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    private String name;

    @Column(nullable = false, length = 500)
    @NotBlank
    private String address;

    @Column(nullable = false, length = 500)
    @NotBlank
    private String schedule;

    @Column(length = 500)
    private String kitchen;

    @Column(nullable = false, unique = true)
    private Long phoneNumber;

}