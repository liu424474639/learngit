package com.biz.lesson.web.controller.student;

import com.biz.lesson.dao.student.SubjectCrudRepository;
import com.biz.lesson.dao.student.SubjectRepository;
import com.biz.lesson.model.user.Subject;
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
@RequestMapping("student/subject")
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private SubjectCrudRepository subjectCrudRepository;

    @RequestMapping("list")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("student/subject/list");
        List<Subject> subjects = subjectRepository.findAll();
        modelAndView.addObject("subjects",subjects);
        return modelAndView;
    }

    @RequestMapping("/add")
    public ModelAndView add(HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("student/subject/add");
        modelAndView.addObject("cmd", "add");
        Subject subject = new Subject();
        modelAndView.addObject("subject", subject);
        return modelAndView;
    }

    @RequestMapping("/save_add")
    public ModelAndView save_add(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("redirect:/list");
        Subject subject = new Subject();
        String subjectId = request.getParameter("subjectId");
        String subjectName = request.getParameter("subjectName");
        String subjectNumber = request.getParameter("subjectNumber");
        String subjectAverage = request.getParameter("subjectAverage");
        subject.setSubjectId(Integer.valueOf(subjectId));
        subject.setStudentName(subjectName);
        subject.setSubjectName(subjectName);
        subject.setSubjectAverage(Integer.valueOf(subjectAverage));
        subjectCrudRepository.save(subject);
        System.out.println(subject.toString());
        return modelAndView;
    }

    @RequestMapping("/save_delete")
    @ResponseBody
    public Boolean save_delete(@RequestParam("subjectId") Integer gradeId) {
        Subject subject = subjectRepository.findSubjectBySubjectId(gradeId);
        System.out.println(subject);
        try {
            subjectCrudRepository.delete(subject);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
