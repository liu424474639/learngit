package com.biz.lesson.web.controller.student;

import com.biz.lesson.business.student.GradeService;
import com.biz.lesson.business.student.StudentAndSubjectService;
import com.biz.lesson.dao.student.GradeCrudRepository;
import com.biz.lesson.dao.student.GradeRepository;
import com.biz.lesson.model.user.Grade;
import com.biz.lesson.model.user.StudentAndSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private GradeService gradeService;

    @Autowired
    private StudentAndSubjectService studentAndSubjectService;

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
        ModelAndView modelAndView = new ModelAndView("redirect:list.do");
        Grade grade = new Grade();
        String gradeName = request.getParameter("gradeName");
//        String gradeNumber = request.getParameter("gradeNumber");
//        String gradeAverage = request.getParameter("gradeAverage");
        grade.setGradeName(gradeName);
//        grade.setGradeNumber(Integer.valueOf(gradeNumber));
//        grade.setGradeAverage(Integer.valueOf(gradeAverage));
        gradeCrudRepository.save(grade);
        System.out.println(grade.toString());
        return modelAndView;
    }

    @RequestMapping("/save_delete")
    @ResponseBody
    public ModelAndView save_delete(@RequestParam("gradeId") Integer gradeId) throws Exception {
        ModelAndView modelAndView = new ModelAndView("redirect:list.do");
        System.out.println(gradeId);
        Grade grade = gradeCrudRepository.findOne(gradeId);
        System.out.println(gradeId);
        System.out.println(grade.toString());
        try {
            gradeCrudRepository.delete(grade);
            return modelAndView;
        } catch (Exception e) {
            return modelAndView;
        }
    }

    @RequestMapping("/update")
    public ModelAndView update(@RequestParam("gradeId") Integer gradeId) throws Exception {
        ModelAndView modelAndView = new ModelAndView("student/grade/update");
        modelAndView.addObject("gradeId",gradeId);
        Grade grade = gradeService.findGrade(gradeId);
        modelAndView.addObject("grade",grade);
        return modelAndView;
    }

    @RequestMapping("save_update")
    @ResponseBody
    public ModelAndView save_update(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("redirect:list.do");
        Integer gradeId = Integer.valueOf(request.getParameter("gradeId"));
        String gradeName = request.getParameter("gradeName");
        int gradeNumber = Integer.parseInt(request.getParameter("gradeNumber"));
        int gradeAverage = Integer.parseInt(request.getParameter("gradeAverage"));
        gradeService.updateGrade(gradeId,gradeNumber,gradeName,gradeAverage);
        return modelAndView;
    }
}
