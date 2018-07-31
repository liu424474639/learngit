package com.biz.lesson.web.controller.student;

import com.biz.lesson.business.student.StudentService;
import com.biz.lesson.dao.student.StudentCrudRepository;
import com.biz.lesson.dao.student.StudentPagingAndSortingRepository;
import com.biz.lesson.dao.student.StudentRepository;
import com.biz.lesson.model.user.Student;
import com.biz.lesson.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

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
    private StudentCrudRepository studentCrudRepository;

    @Autowired
    private StudentPagingAndSortingRepository studentPagingAndSortingRepository;

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
        return modelAndView;
    }

    @RequestMapping("/save_add")
    public ModelAndView save_add(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("redirect:/list");
        Student student = new Student();
        String name = request.getParameter("name");
        String studentId = request.getParameter("studentId");
        String sex = request.getParameter("sex");
        String birthday = request.getParameter("birthday");
        student.setStudentId(Long.valueOf(studentId));
        student.setName(name);
        student.setSex(sex);
        student.setBirthday(birthday);
        studentCrudRepository.save(student);
        System.out.println(student.toString());
        return modelAndView;
    }

    @RequestMapping("/save_delete")
    @ResponseBody
    public Boolean save_delete(@RequestParam("id") Integer id) {
        Student student = studentRepository.findById(id);
        System.out.println(id);
        try {
            studentCrudRepository.delete(student);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}

