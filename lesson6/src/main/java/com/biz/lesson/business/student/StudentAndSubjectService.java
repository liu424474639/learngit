package com.biz.lesson.business.student;

import com.biz.lesson.dao.student.StudentAndSubjectRepository;
import com.biz.lesson.dao.student.StudentAverageCurdRepository;
import com.biz.lesson.model.user.StudentAndSubject;
import com.biz.lesson.model.user.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lyx on 2018/8/4.
 */
@Service
public class StudentAndSubjectService {

    @Autowired
    private StudentAndSubjectRepository studentAndSubjectRepository;

    @Autowired
    private StudentAverageCurdRepository studentAverageCurdRepository;

    public void save(StudentAndSubject studentAndSubject) {
        studentAverageCurdRepository.save(studentAndSubject);
    }

    public void findAll() {
        studentAndSubjectRepository.findAll();
    }

    //获取学科的学生数量
    public Integer findSubjectStudentNumber(String subjectName) {
        List<StudentAndSubject> studentAndSubjectList = studentAndSubjectRepository.findStudentJoinSubjectNumber(subjectName);
        int number = studentAndSubjectList.size() + 1;
        return number;
    }

    //获取学生的选课数量
    public Integer findStudentSubjectNumber(Integer id) {
        List<StudentAndSubject> studentAndSubjectList = studentAndSubjectRepository.findSubjectJoinStudentNumber(id);
        int number = studentAndSubjectList.size() + 1;
        return number;
    }

    //获取学科的平均分
    public Integer findSubjectAverage(String subjectName) {
        int count = 0;
        int total = 0;
        int average = 0;
        List<StudentAndSubject> studentAndSubjects = studentAndSubjectRepository.findStudentJoinSubjectNumber(subjectName);
        for (StudentAndSubject studentAndSubject : studentAndSubjects) {
            total = total + studentAndSubject.getScore();
        }
        if(count != 0){
            average = total/count;
        }
        return average;
    }

    //更新学科学生和分数
    public void updateSubject(Integer studentId,String subjectName,Integer subjectScore) {
        studentAndSubjectRepository.updateSubject(studentId,subjectName,subjectScore);
    }

    //更新学科的平均分
    public void update(String subjectName, Integer score) {
        studentAndSubjectRepository.update(subjectName,score);
    }

    public void delete(StudentAndSubject studentAndSubject) {
        studentAverageCurdRepository.delete(studentAndSubject);
    }

    public List<StudentAndSubject> findStudentSubject(Integer id) {
        List<StudentAndSubject> studentAndSubjects = studentAndSubjectRepository.findSubjectJoinStudentNumber(id);
        return studentAndSubjects;
    }
}
