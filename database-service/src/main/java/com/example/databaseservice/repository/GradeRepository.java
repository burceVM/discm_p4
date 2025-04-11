package com.example.databaseservice.repository;

import com.example.databaseservice.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByUserId(Long userId);
}