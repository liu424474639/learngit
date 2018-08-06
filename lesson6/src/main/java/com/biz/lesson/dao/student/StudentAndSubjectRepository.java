package com.biz.lesson.dao.student;

import com.biz.lesson.model.user.StudentAndSubject;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lyx on 2018/8/4.
 */
@Transactional
public interface StudentAndSubjectRepository extends Repository<StudentAndSubject,Integer>{

    public List<StudentAndSubject> findAll();

    @Query("select o from StudentAndSubject o where o.subjectName=:subjectName")
    public List<StudentAndSubject> findStudentJoinSubjectNumber(@Param("subjectName") String subjectName);

    @Query("select o from StudentAndSubject o where o.studentId=:studentId")
    public List<StudentAndSubject> findSubjectJoinStudentNumber(@Param("studentId") Integer studentId);

    @Modifying
    @Query("update StudentAndSubject o set o.score=:score where o.subjectName=:subjectName")
    public void update(@Param("subjectName") String subjectName, @Param("score") Integer score);

    @Modifying
    @Query("update StudentAndSubject o set o.score=:score where o.studentId=:studentId and o.subjectName=:subjectName")
    public void updateSubject(@Param("studentId") Integer studentId,@Param("subjectName") String subjectName, @Param("score") Integer score);

}
