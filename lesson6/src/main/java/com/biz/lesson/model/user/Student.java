package com.biz.lesson.model.user;

import javax.persistence.*;

/**
 * Student实体类
 */
@Entity
@Table(name = "s_student")
public class Student {


    private Integer id;
    private long StudentId;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 100)
    private String photo;

    @Column(length = 100)
    private String sex;

    @Column(length = 100)
    private String birthday;

    @Column(length = 100)
    private String grade;

    @Column(length = 100)
    private Integer courseNumber;

    @Column(length = 100)
    private Integer average;

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(Integer courseNumber) {
        this.courseNumber = courseNumber;
    }

    public Integer getAverage() {
        return average;
    }

    public void setAverage(Integer average) {
        this.average = average;
    }

    public long getStudentId() {
        return StudentId;
    }

    public void setStudentId(long studentId) {
        StudentId = studentId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", StudentId=" + StudentId +
                ", name='" + name + '\'' +
                ", photo='" + photo + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", grade='" + grade + '\'' +
                ", courseNumber=" + courseNumber +
                ", average=" + average +
                '}';
    }
}
