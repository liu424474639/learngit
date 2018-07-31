package com.biz.lesson.business.student;

import com.biz.lesson.dao.student.GradeCrudRepository;
import com.biz.lesson.dao.student.GradeRepository;
import com.biz.lesson.model.user.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lyx on 2018/7/29.
 */
@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private GradeCrudRepository gradeCrudRepository;

    public void saveGrade(Grade grade){

        gradeCrudRepository.save(grade);
    }

   public void deleteGrade(Integer gradeId){
        gradeCrudRepository.delete(gradeId);
   }

    @Transactional
    public void update(Integer gradeId, String name){
        gradeRepository.update(gradeId,name);
    }
}
