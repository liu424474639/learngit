package com.biz.lesson.model.user;

import javax.persistence.*;

/**
 * Created by lyx on 2018/8/4.
 */
@Entity
@Table(name = "s_subjectScore")
public class StudentAndSubject {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String subjectName;

    private Integer studentId;

    private Integer score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "StudentAndSubject{" +
                "id=" + id +
                ", subjectName='" + subjectName + '\'' +
                ", studentId=" + studentId +
                ", score=" + score +
                '}';
    }
}
