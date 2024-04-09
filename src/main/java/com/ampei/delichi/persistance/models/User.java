package com.ampei.delichi.persistance.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    private String name;

    @Column(nullable = false)
    @NotBlank
    private String lastName;

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @Column(nullable = false)
    @NotBlank
    private String password;

    @Column(nullable = false, unique = true)
    private Long phoneNumber;

}