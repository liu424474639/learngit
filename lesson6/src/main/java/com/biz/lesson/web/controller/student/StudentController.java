package com.biz.lesson.web.controller.student;

import com.biz.lesson.business.student.GradeService;
import com.biz.lesson.business.student.StudentAndSubjectService;
import com.biz.lesson.business.student.StudentService;
import com.biz.lesson.business.student.SubjectService;
import com.biz.lesson.dao.student.*;
import com.biz.lesson.model.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * Created by lyx on 2018/7/30.
 */
@Controller
@RequestMapping("student/user")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private StudentAndSubjectService studentAndSubjectService;

    @RequestMapping("/list")
    public ModelAndView list() throws Exception{
        ModelAndView modelAndView = new ModelAndView("student/student/list");
        List<Student> students = studentRepository.findAll();
        modelAndView.addObject("students",students);
        return modelAndView;
    }

    @RequestMapping("/add")
    public ModelAndView add(HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("student/student/add");
        modelAndView.addObject("cmd", "add");
        Student student = new Student();
        modelAndView.addObject("student", student);
        List<Grade> grades = studentService.findAllGrade();
        modelAndView.addObject("grades",grades);
        return modelAndView;
    }

    @RequestMapping("/save_add")
    public ModelAndView save_add(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("redirect:list.do");
        Student student = new Student();
        String name = request.getParameter("name");
        String studentId = request.getParameter("studentId");
        String sex = request.getParameter("sex");
        String gradeName = request.getParameter("grade");
        String birthday = request.getParameter("birthday");
        student.setStudentId(studentId);
        student.setName(name);
        student.setGrade(gradeName);
        student.setSex(sex);
        student.setBirthday(birthday);
        //保存学生
        studentService.save(student);

        //关联班级更新班级人数
        if(gradeName != null) {
            int gradeNumber;
            if (gradeService.findGradeNumber(gradeName) != null){
                gradeNumber = gradeService.findGradeNumber(gradeName);
            }else{
                gradeNumber = 0;
            }
            gradeNumber = gradeNumber + 1;
            System.out.println(gradeName + gradeNumber);
            Grade grade = gradeService.findGradeByName(gradeName);
            System.out.println(gradeName+"班级名");
            System.out.println(grade.toString());
            grade.getStudents().add(student);
            //更新班级人数
            gradeService.updateGradeNumber(gradeName,gradeNumber);
        }


        System.out.println(student.toString());
        return modelAndView;
    }

    @RequestMapping("/save_delete")
    @ResponseBody
    public ModelAndView save_delete(@RequestParam("id") Integer id) throws Exception{
        ModelAndView modelAndView = new ModelAndView("redirect:list.do");
        Student student = studentService.findStudent(id);
        System.out.println(id);
        try {
            List<StudentAndSubject> studentAndSubjects = studentAndSubjectService.findStudentSubject(id);
            for (StudentAndSubject studentAndSubject : studentAndSubjects) {
                studentAndSubjectService.delete(studentAndSubject);
            }
            studentService.deleteById(id);
            return modelAndView;
        } catch (Exception e) {
            return modelAndView;
        }
    }

    @RequestMapping("/select_subject")
    public ModelAndView selectSubject(@RequestParam("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView("student/student/selectSubject");
        modelAndView.addObject("studentId",id);
        List<Subject> subjects = subjectRepository.findAll();
        modelAndView.addObject("subjects",subjects);
        return modelAndView;
    }

    @RequestMapping("save_subject")
    @ResponseBody
    public ModelAndView saveSubject(@RequestParam("studentId") Integer id,HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("redirect:list.do");
        String selectSubject = request.getParameter("selectSubject");
        if (!selectSubject.equals("null")){
            String[] subjects = selectSubject.split(",");
            Student student = studentService.findStudent(id);
            int courseNumber = subjects.length;
//        获取到选课个数，根据id跟新选课个数字段值
            studentService.update(id,courseNumber);
            System.out.println("保存成功");


            for(String s : subjects){
                //保存学生选的课
                StudentAndSubject studentAndSubject = new StudentAndSubject();
                studentAndSubject.setSubjectName(s);
                studentAndSubject.setStudentId(id);
                studentAndSubjectService.save(studentAndSubject);

                //更新选修课人数
                int subjectNumber = subjectService.findSubjectNumber(s);
                Subject subject = subjectService.findSubject(s);
                subject.setSubjectNumber(subjectNumber + 1);
                //建立关联
                subject.getStudents().add(student);
                subjectService.saveSubject(subject);
            }
        }
        return modelAndView;
    }

    @RequestMapping("/doScore")
    public ModelAndView doScore(@RequestParam("id") Integer studentId) {
        ModelAndView modelAndView = new ModelAndView("student/student/doScore");
        Student student = studentService.findStudent(studentId);
        Iterator<Subject> it = student.getSubjects().iterator();
        List<String> subjects = new ArrayList<String>();
        while (it.hasNext()){
            Subject subject = it.next();
            System.out.print("8号的所有学科：");
            subjects.add(subject.getSubjectName());
            System.out.println(subject.getSubjectName()+",");
        }
        modelAndView.addObject("studentId",studentId);
        modelAndView.addObject("subjects",subjects);
        return modelAndView;
    }

    @RequestMapping("/save_doScore")
    public ModelAndView save_doScore(@RequestParam("studentId") Integer id,HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("redirect:list.do");
        Enumeration<String> names = request.getParameterNames();
        int total = 0;
        int count = 0;
        int studentAverage = 0;
        int score = 0;
        while (names.hasMoreElements()) {
            String strings = (String) names.nextElement();
            System.out.println("信息:" + strings);
            if(!strings.equals("studentId")){
                score = Integer.parseInt(request.getParameter(strings));
                System.out.println("分数：" + score);
                total = total + score;
                count = count + 1;
                System.out.println("总分数为：" + total);
                System.out.println("人数为：" + count);
                //更新每个学科学生的分数
                studentAndSubjectService.updateSubject(id,strings,score);

                System.out.println("保存学生成功");
                //更新学科的平均分(ok)
                subjectService.updateSubjectAverage(strings,score);

            }

        }
        if(count != 0){
            studentAverage = (total)/(count);
        }
        System.out.println("总成绩：" + total);
        System.out.println("总个数" + count);
        System.out.println("平均分：" + studentAverage);
        //更新学生平均分
        studentService.updateAverage(id,studentAverage);

        //更新班级平均分
        Student student = studentService.findStudent(id);
        String gradeName = student.getGrade();
        gradeService.updateGradeAverage(gradeName,studentAverage);
        return modelAndView;
    }

    @RequestMapping("/update")
    public ModelAndView update(@RequestParam("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView("student/student/update");
        modelAndView.addObject("id",id);
        Student student = studentService.findStudent(id);
        modelAndView.addObject("student", student);
        List<Grade> grades = studentService.findAllGrade();
        modelAndView.addObject("grades",grades);
        return modelAndView;
    }

    @RequestMapping("/save_update")
    public ModelAndView save_update(@RequestParam("Id") Integer id,HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("redirect:list.do");
        String name = request.getParameter("name");
        String studentId = request.getParameter("studentId");
        String sex = request.getParameter("sex");
        String grade = request.getParameter("grade");
        String birthday = request.getParameter("birthday");
        Integer courseNumber = Integer.valueOf(request.getParameter("courseNumber"));
        Integer average = Integer.valueOf(request.getParameter("average"));
        studentService.updateAll(id,name,sex,studentId,birthday,grade,courseNumber,average);
        return modelAndView;
    }

}

