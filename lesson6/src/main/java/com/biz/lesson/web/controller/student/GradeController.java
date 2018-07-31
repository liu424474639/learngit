package com.biz.lesson.web.controller.student;

import com.biz.lesson.dao.student.GradeCrudRepository;
import com.biz.lesson.dao.student.GradeRepository;
import com.biz.lesson.model.user.Grade;
import com.biz.lesson.model.user.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyx on 2018/7/31.
 */
@Controller
@RequestMapping("student/grade")
public class GradeController {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private GradeCrudRepository gradeCrudRepository;

    @RequestMapping("/list")
    public ModelAndView list() throws Exception {
        ModelAndView modelAndView = new ModelAndView("student/grade/list");
        List<Grade> grades = gradeRepository.findAll();
        modelAndView.addObject("grades",grades);
        return modelAndView;
    }

    @RequestMapping("/add")
    public ModelAndView add(HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("student/grade/add");
        modelAndView.addObject("cmd", "add");
        Grade grade = new Grade();
        modelAndView.addObject("grade", grade);
        return modelAndView;
    }

    @RequestMapping("/save_add")
    public ModelAndView save_add(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("redirect:/list");
        Grade grade = new Grade();
        String gradeId = request.getParameter("gradeId");
        String gradeName = request.getParameter("gradeName");
        String gradeNumber = request.getParameter("gradeNumber");
        String gradeAverage = request.getParameter("gradeAverage");
        grade.setGradeId(Integer.valueOf(gradeId));
        grade.setGradeName(gradeName);
        grade.setGradeNumber(Integer.valueOf(gradeNumber));
        grade.setGradeAverage(Integer.valueOf(gradeAverage));
        gradeCrudRepository.save(grade);
        System.out.println(grade.toString());
        return modelAndView;
    }

    @RequestMapping("/save_delete")
    @ResponseBody
    public Boolean save_delete(@RequestParam("gradeId") Integer gradeId) {
        Grade grade = gradeRepository.findGradeByGradeId(gradeId);
        System.out.println(grade);
        try {
            gradeCrudRepository.delete(grade);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
