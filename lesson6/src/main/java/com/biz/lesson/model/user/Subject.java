package com.biz.lesson.model.user;

import javax.persistence.*;

/**
 * Created by lyx on 2018/7/29.
 */
@Entity
@Table(name = "s_subject")
public class Subject {


    private Integer subjectId;

    @Column(length = 100)
    private String subjectName;

    @Column(length = 100)
    private String studentName;

    @Column(length = 100)
    private Integer SubjectAverage;

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getSubjectAverage() {
        return SubjectAverage;
    }

    public void setSubjectAverage(Integer subjectAverage) {
        SubjectAverage = subjectAverage;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectId=" + subjectId +
                ", subjectName='" + subjectName + '\'' +
                ", studentName='" + studentName + '\'' +
                ", SubjectAverage=" + SubjectAverage +
                '}';
    }
}
