package com.biz.lesson.business.student;

import com.biz.lesson.dao.student.GradeCrudRepository;
import com.biz.lesson.dao.student.GradeRepository;
import com.biz.lesson.model.user.Grade;
import com.biz.lesson.model.user.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

/**
 * Created by lyx on 2018/7/29.
 */
@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private GradeCrudRepository gradeCrudRepository;

    public void saveGrade(Grade grade){

        gradeCrudRepository.save(grade);
    }

   public void deleteGrade(Integer gradeId){
        gradeCrudRepository.delete(gradeId);
   }

    @Transactional
    public void update(Integer gradeId, String name){
        gradeRepository.update(gradeId,name);
    }

    @Transactional
    public void updateGradeNumber(String gradeName,Integer gradeNumber) {
        gradeRepository.updateGradeNumber(gradeName,gradeNumber);
    }

    @Transactional
    public void updateGrade(Integer gradeId,Integer gradeNumber, String gradeName, Integer gradeAverage) {
        gradeRepository.updateGrade(gradeId,gradeNumber,gradeName,gradeAverage);
    }

    @Transactional
    public void updateGradeAverage(String gradeName,Integer newAverage) {

        int count = 0;
        int total = 0;
        int average = 0;
        Grade grade = gradeRepository.findGrade(gradeName);
        System.out.println("班级为：" + grade.toString());
        Iterator<Student> it = grade.getStudents().iterator();
        while(it.hasNext()){
            System.out.println("在" + grade.getGradeName() + "的是：");
            Student student = it.next();
            System.out.println("名字是");
            System.out.println(student.getName());
            System.out.println("分数是");
            if (student.getAverage()!=null){
                average = student.getAverage();
            }else {
                average = 0;
            }
            System.out.println(student.getAverage());
            count = count + 1;
            total = total + average;
        }
        int gradeNumber = grade.getGradeNumber();
        average = (total+newAverage)/(gradeNumber);
        System.out.println("总共几个人：" + gradeNumber);
        System.out.println("总分数：" + total);
        System.out.println("学科平均分为：" + average);
        gradeRepository.updateGradeAverage(gradeName,average);

    }

    public Grade findGrade(Integer gradeId) {
        Grade grade = gradeCrudRepository.findOne(gradeId);
        return grade;
    }

    public Grade findGradeByName(String gradeName) {
        Grade grade = gradeRepository.findGrade(gradeName);
        return grade;
    }

    public void findAll() {
        Iterable<Grade> grades = gradeCrudRepository.findAll();
    }

    //查找班级的学生都有哪些
    public void findStudentJoinGrade(String gradename){
        Grade grade = gradeRepository.findGrade(gradename);
        Iterator<Student> it = grade.getStudents().iterator();
        while ((it.hasNext())){
            Student student = it.next();
            System.out.println("选数学的人");
            System.out.println(student.getName());
            System.out.println(student.getAverage());
        }
    }

    //查找班级的平均分
    public Integer findGradeAverage(String gradeName) {
        int count = 0;
        int total = 0;
        int average = 0;
        Grade grade = gradeRepository.findGrade(gradeName);
        System.out.println("班级为：" + grade.toString());
        Iterator<Student> it = grade.getStudents().iterator();
        while(it.hasNext()){
            System.out.println("在" + grade.getGradeName() + "的是：");
            Student student = it.next();
            System.out.println("名字是");
            System.out.println(student.getName());
            System.out.println("分数是");
            if (student.getAverage()!=null){
                average = student.getAverage();
            }

            System.out.println(student.getAverage());
            count = count + 1;
            total = total + average;
        }
        average = total/count;
        System.out.println("总共几个人：" + count);
        System.out.println("总分数：" + total);
        System.out.println("班级平均分为：" + average);
        return average;
    }

    //查找班级的人数
    public Integer findGradeNumber(String gradeName) {
        int count = 0;
        Grade grade = gradeRepository.findGrade(gradeName);
        Iterator<Student> it = grade.getStudents().iterator();
        while(it.hasNext()){
            Student student = it.next();
            System.out.println("名字是");
            System.out.println(student.getName());
            count = count + 1;
        }
        System.out.println("总共几个人：" + count);
        return count;
    }
}
