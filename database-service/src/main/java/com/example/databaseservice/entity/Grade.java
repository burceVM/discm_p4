package com.example.databaseservice.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "grades")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "course_code")
    private String courseCode;
    @Column(name = "course_grade")
    private String courseGrade;
    private String term;
    private int year;

    public Grade() {} // no argument constructor because JPA requires it

    public Grade(Long userId, String courseCode, String courseGrade, String term, int year) {
        this.userId = userId;
        this.courseCode = courseCode;
        this.courseGrade = courseGrade;
        this.term = term;
        this.year = year;
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

    public String getCourseGrade() {
        return courseGrade;
    }
    public void setCourseGrade(String courseGrade) {
        this.courseGrade = courseGrade;
    }

    public String getTerm() {
        return term;
    }
    public void setTerm(String term) {
        this.term = term;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
}