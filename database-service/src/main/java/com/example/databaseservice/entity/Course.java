package com.example.databaseservice.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseCode;
    private String courseName;
    private String term;
    
    public Course() {} // no argument constructor because JPA requires it

    public Course(String courseCode, String courseName, String term) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.term = term;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseCode() {
        return courseCode;
    }
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTerm() {
        return term;
    }
    public void setTerm(String term) {
        this.term = term;
    }
}