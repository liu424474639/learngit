package com.biz.lesson.dao.student;

import com.biz.lesson.model.user.Student;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by lyx on 2018/7/29.
 */
public interface StudentPagingAndSortingRepository extends PagingAndSortingRepository<Student,Integer> {
}
