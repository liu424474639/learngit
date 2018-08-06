package com.biz.lesson.model.user;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lyx on 2018/7/29.
 */
@Entity
@Table(name = "s_subject")
public class Subject {


    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Integer subjectId;

    @Column(length = 100)
    private String subjectName;

    @Column(length = 100)
    //这里本应该是subjectNumber，即选课个数，在此以studentName代替选课个数
    private Integer subjectNumber;

    @Column(length = 100)
    private Integer SubjectAverage;

    @ManyToMany(targetEntity=Student.class,cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "student_subject", joinColumns = {
            @JoinColumn(name = "subjectId", referencedColumnName = "subjectId")},inverseJoinColumns = {
            @JoinColumn(name = "id", referencedColumnName = "id")})
    private Set<Student> students = new HashSet<Student>();

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

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

    public Integer getSubjectNumber() {
        return subjectNumber;
    }

    public void setSubjectNumber(Integer subjectNumber) {
        this.subjectNumber = subjectNumber;
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
                ", subjectNumber='" + subjectNumber + '\'' +
                ", SubjectAverage=" + SubjectAverage +
                ", students=" + students +
                '}';
    }
}
