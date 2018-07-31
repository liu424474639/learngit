package com.biz.lesson.dao.student;

import com.biz.lesson.model.user.Subject;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by lyx on 2018/7/28.
 */
public interface SubjectCrudRepository extends CrudRepository<Subject,Integer> {
}
