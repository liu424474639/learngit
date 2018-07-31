package com.biz.lesson.model.user;

import javax.persistence.*;

/**
 * Created by lyx on 2018/7/29.
 */
@Entity
@Table(name = "s_grade")
public class Grade {

    private Integer gradeId;

    @Column(length = 100)
    private String gradeName;

    @Column(length = 100)
    private Integer gradeNumber;

    @Column(length = 100)
    private Integer gradeAverage;

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
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
