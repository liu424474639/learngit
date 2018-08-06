package com.biz.lesson.dao.student;

import com.biz.lesson.model.user.StudentAndSubject;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by lyx on 2018/8/5.
 */
public interface StudentAverageCurdRepository extends CrudRepository<StudentAndSubject,Integer>{
}
