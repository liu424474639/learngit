package com.biz.lesson.dao.student;

import com.biz.lesson.business.student.GradeService;
import com.biz.lesson.business.student.StudentService;
import com.biz.lesson.business.student.SubjectService;
import com.biz.lesson.model.user.Grade;
import com.biz.lesson.model.user.Student;
import com.biz.lesson.model.user.StudentAndSubject;
import com.biz.lesson.model.user.Subject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by lyx on 2018/7/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/application-content.xml")
public class StudentCrudRepositoryTest {

    private ApplicationContext ctx;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private SubjectCrudRepository subjectCrudRepository;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private StudentAverageCurdRepository studentAverageCurdRepository;

    @Autowired
    private StudentAndSubjectRepository studentAndSubjectRepository;

    @Autowired
    private StudentPagingAndSortingRepository studentPagingAndSortingRepository;



    @Before
    public void before(){
        ctx = new ClassPathXmlApplicationContext("application-content.xml");
        gradeService = ctx.getBean(GradeService.class);
        System.out.println("step");
    }

    @After
    public void after(){
        ctx = null;
        System.out.println("testDown");
    }


    @Test
    public void test1() {
        Student s = new Student();
        s = studentRepository.findById(6);
        Set<Student> stu = new HashSet<Student>();
        stu.add(s);
        Grade grade = new Grade();
        grade = gradeService.findGrade(2);
        grade.getStudents().add(s);
        System.out.println("获取的结果：" + grade.getGradeId());
        gradeService.saveGrade(grade);
        System.out.println("插入成功");
    }

    @Test
    public void findAll() {
        List<Student> students = studentRepository.findAll();
        for (Student student : students) {
            Iterator<Grade> it = student.getGrades().iterator();
            while(it.hasNext()){
                System.out.println("开始查询");
                Grade grade = it.next();
                System.out.println("查询结果是");
                System.out.println(grade.getGradeName());
            }
        }
    }

    @Test
    public void findOne() {
        Student student = studentRepository.findById(6);
        Iterator<Grade> it = student.getGrades().iterator();
        while (it.hasNext()){
            Grade grade = it.next();
            System.out.print("6号的所有班级：");
            System.out.println(grade.getGradeName()+",");
            System.out.println(grade.getGradeNumber());
        }
    }

    @Test
    public void findOneSubject() {
        Student student = studentRepository.findById(8);
        Iterator<Subject> it = student.getSubjects().iterator();
        while (it.hasNext()){
            Subject subject = it.next();
            System.out.print("8号的所有学科：");
            System.out.println(subject.getSubjectName()+",");
            System.out.println(subject.getSubjectAverage());
        }
    }

    @Test
    public void findOneStudent() {
        Subject subject = subjectRepository.findByName("地理");
        Iterator<Student> it = subject.getStudents().iterator();
        while ((it.hasNext())){
            Student student = it.next();
            System.out.println("选数学的人");
            System.out.println(student.getName());
            System.out.println(student.getAverage());
        }
    }

    @Test
    public void savess() {
        Grade grade = new Grade();
        grade = gradeService.findGrade(1);
        // Integer id = memberService.save(member);
        Set<Grade> st = new HashSet<Grade>();
        st.add(grade);
        Student student = new Student();
        student = studentRepository.findById(6);
        //ManyToMang的多对多测插入
        student.getGrades().add(grade);
        studentService.save(student);


        //LOGGER.info(JSON.toJSONString(id));
    }

    @Test
    public void findAllGrade() {
        List<Grade> grades = studentService.findAllGrade();
        for (Grade grade : grades) {
            System.out.println("结果是");
            System.out.println(grade.toString());
        }
    }

    @Test
    public void findSubject() {
        Subject subject = subjectRepository.findByName("数学");
        System.out.println(subject.toString());
    }

    @Test
    public void findGrade() {
        int count = 0;
        int total = 0;
        String gradeName = "A班";
        Grade grade = gradeRepository.findGrade(gradeName);
        System.out.println("班级为：" + grade.toString());
        Iterator<Student> it = grade.getStudents().iterator();
        while(it.hasNext()){
            System.out.println("在A班的是：");
            Student student = it.next();
            System.out.println("名字是");
            System.out.println(student.getName());
            System.out.println("分数是");
            int average = 0;
            if (student.getAverage()!=null){
                average = student.getAverage();
            }

            System.out.println(student.getAverage());
            count = count + 1;
            total = total + average;
        }
        System.out.println("总共几个人：" + count);
        System.out.println("总分数：" + total);
    }

    @Test
    public void findGradeNumber() {
        int count = 0;
        String gradeName = "A班";
        Grade grade = gradeRepository.findGrade(gradeName);
        System.out.println("班级为：" + grade.toString());
        Iterator<Student> it = grade.getStudents().iterator();
        while(it.hasNext()){
            System.out.println("在A班的是：");
            Student student = it.next();
            System.out.println("名字是");
            System.out.println(student.getName());
            System.out.println("分数是");
            System.out.println(student.getAverage());
            count = count + 1;
        }
        System.out.println("总共几个人：" + count);
    }

    //查找学科的平均分
    @Test
    public void findSubjectAverage() {
        int count = 0;
        int total = 0;
        int average = 0;
        String subjectName = "天文";
        Subject subject = subjectRepository.findSubject(subjectName);
        System.out.println("班级为：" + subject.toString());
        Iterator<Student> it = subject.getStudents().iterator();
        while(it.hasNext()){
            System.out.println("在" + subject.getSubjectName() + "的是：");
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
        System.out.println("学科平均分为：" + average);
    }

    @Test
    public void findNumber() {
        StudentAndSubject studentAndSubject = new StudentAndSubject();
        studentAndSubject.setSubjectName("数学");
        studentAndSubject.setStudentId(1);
        studentAndSubject.setScore(78);
        studentAverageCurdRepository.save(studentAndSubject);

        StudentAndSubject studentAndSubject1 = new StudentAndSubject();
        studentAndSubject.setSubjectName("数学");
        studentAndSubject.setStudentId(2);
        studentAndSubject.setScore(67);
        studentAverageCurdRepository.save(studentAndSubject1);

        StudentAndSubject studentAndSubject2 = new StudentAndSubject();
        studentAndSubject.setSubjectName("数学");
        studentAndSubject.setStudentId(3);
        studentAndSubject.setScore(67);
        studentAverageCurdRepository.save(studentAndSubject2);

        String subjectName = "数学";
        List<StudentAndSubject> studentAndSubjectList = studentAndSubjectRepository.findStudentJoinSubjectNumber(subjectName);
        int number = studentAndSubjectList.size();
        System.out.println("数量为：" + number);

    }

    @Test
    public void delete() {
        studentAverageCurdRepository.deleteAll();
    }

    @Test
    public void updateGradeAverage() {
        String gradeName = "D班";
        int newAverage = 100;
        int count = 0;
        int total = 0;
        int average = 0;
        Grade grade = gradeRepository.findGrade(gradeName);
        System.out.println("班级为：" + grade.toString());
        Iterator<Student> it = grade.getStudents().iterator();
        while (it.hasNext()) {
            System.out.println("在" + grade.getGradeName() + "的是：");
            Student student = it.next();
            System.out.println("名字是");
            System.out.println(student.getName());
            System.out.println("分数是");
            if (student.getAverage() != null) {
                average = student.getAverage();
            } else {
                average = 0;
            }
            System.out.println(student.getAverage());
            count = count + 1;
            total = total + average;
        }
        int gradeNumber = grade.getGradeNumber();
        average = (total + newAverage) / (gradeNumber);
        System.out.println("总共几个人：" + count);
        System.out.println("总分数：" + total);
        System.out.println("学科平均分为：" + average);
    }

    @Test
    public void pageStudent() {
        Sort.Order order = new Sort.Order(Sort.Direction.DESC,"id");
        Sort sort = new Sort(order);
        Pageable pageable = new PageRequest(0,5,sort);
        Page<Student> page = studentPagingAndSortingRepository.findAll(pageable);
        System.out.println("查询的总页数" + page.getTotalPages());
        System.out.println("查询的总记录数" + page.getTotalElements());
        System.out.println("查询的当前第几页" + (page.getNumber() + 1));
        System.out.println("查询的当前页面的集合" + page.getContent());
        System.out.println("查询当前页面的记录数" + page.getNumberOfElements());
    }

    @Test
    public void findStudentSubject(){
        int id = 8;
        Student student = studentRepository.findById(id);
        List<StudentAndSubject> studentAndSubjects = studentAndSubjectRepository.findSubjectJoinStudentNumber(id);
        for (StudentAndSubject studentAndSubject : studentAndSubjects) {
            System.out.println("8号的选课有");
            studentAverageCurdRepository.delete(studentAndSubject);
            System.out.println("删除成功");
        }
    }
}