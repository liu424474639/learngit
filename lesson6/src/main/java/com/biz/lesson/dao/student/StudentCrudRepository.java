package com.biz.lesson.dao.student;

import com.biz.lesson.model.user.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by lyx on 2018/7/28.
 */
public interface StudentCrudRepository extends CrudRepository<Student,Integer> {
}
