package com.biz.lesson.dao.student;

import com.biz.lesson.business.student.StudentService;
import com.biz.lesson.model.user.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by lyx on 2018/7/30.
 */
public class StudentCrudRepositoryTest {
    String conf = "application-content.xml";
    ApplicationContext ac = new ClassPathXmlApplicationContext(conf);

    private StudentService studentService;



}