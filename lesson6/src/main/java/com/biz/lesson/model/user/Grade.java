package com.biz.lesson.model.user;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lyx on 2018/7/29.
 */
@Entity
@Table(name = "s_grade")
public class Grade {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Integer gradeId;

    @Column(length = 100)
    private String gradeName;

    @Column(length = 100)
    private Integer gradeNumber;

    @Column(length = 100)
    private Integer gradeAverage;


    @ManyToMany(targetEntity=Student.class,cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "student_grade", joinColumns = {
            @JoinColumn(name = "gradeId", referencedColumnName = "gradeId")},inverseJoinColumns = {
            @JoinColumn(name = "id", referencedColumnName = "id")})
    private Set<Student> students = new HashSet<Student>();

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }


    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public Integer getGradeNumber() {
        return gradeNumber;
    }

    public void setGradeNumber(Integer gradeNumber) {
        this.gradeNumber = gradeNumber;
    }

    public Integer getGradeAverage() {
        return gradeAverage;
    }

    public void setGradeAverage(Integer gradeAverage) {
        this.gradeAverage = gradeAverage;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "gradeId=" + gradeId +
                ", gradeName='" + gradeName + '\'' +
                ", gradeNumber=" + gradeNumber +
                ", gradeAverage=" + gradeAverage +
                '}';
    }
}
