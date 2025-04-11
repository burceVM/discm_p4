package com.example.databaseservice.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "faculty")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // getters and setters
}
