package com.biz.lesson.business.student;

import com.biz.lesson.dao.student.*;
import com.biz.lesson.model.user.Grade;
import com.biz.lesson.model.user.Student;
import com.biz.lesson.model.user.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lyx on 2018/7/28.
 */
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentCrudRepository studentCrudRepository;

    @Autowired
    private GradeCrudRepository gradeCrudRepository;

    @Autowired
    private SubjectCrudRepository subjectCrudRepository;

    @Autowired
    private StudentPagingAndSortingRepository studentPagingAndSortingRepository;

//    @Transactional
//    public void update(Integer id, String name){
//        studentRepository.update(id,name);
//    }

//    保存所有对象需要用saveAll
    public void saveAll(List<Student> students){
        studentCrudRepository.save(students);
    }

    public void save(Student student){
        studentCrudRepository.save(student);
    }

    public void deleteById(Integer id) {
        studentCrudRepository.delete(id);
    }

    public void deleteAll(){
        studentCrudRepository.deleteAll();
    }

    public Student findStudent(Integer studentId) {
        Student student = studentCrudRepository.findOne(studentId);
        return student;
    }

    public List<Grade> findAllGrade() {

        Iterator<Grade> grades = gradeCrudRepository.findAll().iterator();
        List<Grade> grades1 = new ArrayList<Grade>();
        while (grades.hasNext()){
            Grade grade = grades.next();
            grades1.add(grade);
        }
        return grades1;

    }

    public List<Subject> findAllSubject(Integer id) {

        Student student = studentCrudRepository.findOne(id);
        Iterator<Subject> sub = student.getSubjects().iterator();
        List<Subject> subjects = new ArrayList<Subject>();
        while (sub.hasNext()){
            Subject subject = sub.next();
            subjects.add(subject);
        }
        return subjects;

    }

    //根据id更新课程个数
    @Transactional
    public void update(Integer id ,Integer courseNumber) {
        studentRepository.update(id,courseNumber);
    }

    //根据id更新平均分
    @Transactional
    public void updateAverage(Integer id, Integer average){
        studentRepository.updateAverage(id,average);
    }

    //修改所有
    @Transactional
    public void updateAll(Integer id,String name, String sex, String studentId,String birthday,String grade,Integer courseNumber,Integer average) {
        studentRepository.updateAll(id,name,sex,studentId,birthday,grade,courseNumber,average);
    }

    @Transactional
    public Page<Student> pageStudent(Integer page, Integer pageSize) {
        Sort.Order order = new Sort.Order(Sort.Direction.DESC,"id");
        Sort sort = new Sort(order);
        Pageable pageable = new PageRequest(page,pageSize,sort);
        Page<Student> students = studentPagingAndSortingRepository.findAll(pageable);
        System.out.println("查询的总页数" + students.getTotalPages());
        System.out.println("查询的总记录数" + students.getTotalElements());
        System.out.println("查询的当前第几页" + (students.getNumber() + 1));
        System.out.println("查询的当前页面的集合" + students.getContent());
        System.out.println("查询当前页面的记录数" + students.getNumberOfElements());
        System.out.println("是否有上一页" + students.isFirst());
        return students;
    }

}
