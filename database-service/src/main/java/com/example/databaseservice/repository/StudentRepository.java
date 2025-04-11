package com.example.databaseservice.repository;

import com.example.databaseservice.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}

