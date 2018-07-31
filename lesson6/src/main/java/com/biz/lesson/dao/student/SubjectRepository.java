package com.biz.lesson.dao.student;

import com.biz.lesson.model.user.Subject;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lyx on 2018/7/28.
 */

@Transactional
public interface SubjectRepository extends Repository<Subject,Integer> {

    public Subject findSubjectBySubjectId(Integer subjectId);
    public List<Subject> findAll();
    public List<Subject> findSubjectByStudentName(String subjectName);

    @Modifying
    @Query("update Subject o set o.subjectAverage=:average where o.studentName=:name")
    public void update(@Param("average") Integer average, @Param("name") String name);

    @Modifying
    @Query("update Subject o set o.subjectAverage=:average where o.studentName=:name")
    public void updateAll(@Param("average") Integer average, @Param("name") String name);

}
