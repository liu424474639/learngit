package com.biz.lesson.business.student;

import com.biz.lesson.dao.student.SubjectCrudRepository;
import com.biz.lesson.dao.student.SubjectRepository;
import com.biz.lesson.model.user.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lyx on 2018/7/29.
 */
@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private SubjectCrudRepository subjectCrudRepository;

    public void saveSubject(Subject subject){

        subjectCrudRepository.save(subject);
    }

    public void saveAll(List<Subject> subjects) {
        subjectCrudRepository.save(subjects);
    }

   public void deleteSubject(Integer subjectId){
       subjectCrudRepository.delete(subjectId);
   }

    @Transactional
    public void update(Integer average, String name){
        subjectRepository.update(average,name);
    }

//    public void updateAll(Integer average,String subjectName,String studentName){
//
//    }
}
