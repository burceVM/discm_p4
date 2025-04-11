package com.example.databaseservice.repository;

import com.example.databaseservice.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByCourseCode(String courseCode);
    List<Course> findByTerm(String term);
}