package com.biz.lesson.dao.student;

import com.biz.lesson.business.student.GradeService;
import com.biz.lesson.model.user.Grade;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by lyx on 2018/8/1.
 */
public class GradeRepositoryTest {

    private ApplicationContext ctx = null;

    @Autowired
    private  GradeRepository gradeRepository;

    @Autowired
    private GradeCrudRepository gradeCrudRepository;

    @Autowired
    private GradeService gradeService;

    @Before
    public void before(){
        ctx = new ClassPathXmlApplicationContext("application-content.xml");
        gradeCrudRepository = ctx.getBean(GradeCrudRepository.class);
        System.out.println("step");
    }

    @After
    public void after(){
        ctx = null;
        System.out.println("testDown");
    }

    @Test
    public void findGradeByGradeId() throws Exception {


        Grade grade = gradeCrudRepository.findOne(2);

            System.out.println(grade.toString());

    }

    @Test
    public void findAll() {
        Iterator<Grade> grades = gradeCrudRepository.findAll().iterator();
        while (grades.hasNext()){
            Grade grade = grades.next();
            System.out.println("结果为");
            System.out.println(grade.getGradeName());
        }
    }

    @Test
    public void findOne() {
        String gradeName = "C班";
        Grade grade = gradeRepository.findGradeByGradeId(3);
        System.out.println("班级为：" + grade.toString());
    }

}