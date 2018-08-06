package com.biz.lesson.web.controller.student;

import com.biz.lesson.business.student.SubjectService;
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

    @Autowired
    private SubjectService subjectService;

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
        ModelAndView modelAndView = new ModelAndView("redirect:list.do");
        Subject subject = new Subject();
        String subjectName = request.getParameter("subjectName");
//        String subjectNumber = request.getParameter("subjectNumber");
//        String subjectAverage = request.getParameter("subjectAverage");
//        subject.setStudentName(subjectName);
        subject.setSubjectName(subjectName);
//        subject.setSubjectAverage(Integer.valueOf(subjectAverage));
        subjectCrudRepository.save(subject);
        System.out.println(subject.toString());
        return modelAndView;
    }

    @RequestMapping("/save_delete")
    @ResponseBody
    public ModelAndView save_delete(@RequestParam("subjectId") Integer subjectId) throws Exception{
        ModelAndView modelAndView = new ModelAndView("redirect:list.do");
        Subject subject = subjectCrudRepository.findOne(subjectId);
        System.out.println(subject);
        try {
            subjectCrudRepository.delete(subject);
            return modelAndView;
        } catch (Exception e) {
            return modelAndView;
        }
    }

    @RequestMapping("/update")
    public ModelAndView update(@RequestParam("subjectId") Integer subjectId) throws Exception {
        ModelAndView modelAndView = new ModelAndView("student/subject/update");
        modelAndView.addObject("subjectId",subjectId);
        Subject subject = subjectService.findSubject(subjectId);
        modelAndView.addObject("subject",subject);
        return modelAndView;
    }

    @RequestMapping("save_update")
    @ResponseBody
    public ModelAndView save_update(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("redirect:list.do");
        Integer subjectId = Integer.valueOf(request.getParameter("subjectId"));
        String subjectName = request.getParameter("subjectName");
        int subjectNumber = Integer.parseInt(request.getParameter("subjectNumber"));
        int subjectAverage = Integer.parseInt(request.getParameter("subjectAverage"));
        subjectService.updateSubject(subjectId,subjectName,subjectNumber,subjectAverage);
        return modelAndView;
    }

}
