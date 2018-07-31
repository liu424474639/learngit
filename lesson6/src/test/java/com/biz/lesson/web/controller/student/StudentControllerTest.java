package com.biz.lesson.web.controller.student;

import com.biz.lesson.business.student.StudentService;
import com.biz.lesson.dao.student.StudentRepository;
import com.biz.lesson.model.user.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by lyx on 2018/7/30.
 */
public class StudentControllerTest {

    private ApplicationContext ctx = null;
    private StudentRepository studentRepository = null;

    @Before
    public void before(){
        ctx = new ClassPathXmlApplicationContext("application-content.xml");
        studentRepository = ctx.getBean(StudentRepository.class);
        System.out.println("step");
    }

    @After
    public void after(){
        ctx = null;
        System.out.println("testDown");
    }

    @Test
    public void testFindAll(){
        List<Student> students = studentRepository.findAll();
        for(Student student : students){
            System.out.println("id:" + student.getId() +
                    ", name:" + student.getName() +
                    ", photo:" + student.getPhoto() +
                    ",sex:" + student.getSex() +
                    ", birthday:" + student.getBirthday() +
                    ", grade:" + student.getGrade() +
                    ", courseNumber:" + student.getCourseNumber() +
                    ", average:" + student.getAverage());
        }
    }

}