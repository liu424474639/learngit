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
    public Subject findSubjectBySubjectName(String subjectName);

    @Query("select o from Subject o where o.subjectName=?1")
    public Subject findByName(String subjectName);
//    @Modifying
//    @Query("update Subject o set o.subjectAverage=:average where o.studentName=:name")
//    public void update(@Param("average") Integer average, @Param("name") String name);
//
//    @Modifying
//    @Query("update Subject o set o.subjectAverage=:average where o.studentName=:name")
//    public void updateAll(@Param("average") Integer average, @Param("name") String name);
    @Modifying
    @Query("update Subject o set o.subjectName=:subjectName, o.subjectNumber=:subjectNumber, o.SubjectAverage=:subjectAverage where o.subjectId=:subjectId")
    public void update(@Param("subjectId") Integer subjectId,@Param("subjectName") String subjectName, @Param("subjectNumber") Integer subjectNumber,@Param("subjectAverage") Integer subjectAverage);

    @Modifying
    @Query("update Subject o set o.subjectNumber=:subjectNumber where o.subjectId=:subjectId")
    public void updateSubjectNumber(@Param("subjectId") Integer subjectId, @Param("subjectNumber") Integer subjectNumber);

    @Modifying
    @Query("update Subject o set o.SubjectAverage=:subjectAverage where o.subjectId=:subjectId")
    public void updateSubjectAverage(@Param("subjectId") Integer subjectId, @Param("subjectAverage") Integer subjectAverage);

    @Modifying
    @Query("update Subject o set o.SubjectAverage=:subjectAverage where o.subjectName=:subjectName")
    public void updateSubjectAverage(@Param("subjectName") String subjectName, @Param("subjectAverage") Integer subjectAverage);

    @Query("select o from Subject o where o.subjectName=:subjectName")
    public Subject findSubject(@Param("subjectName") String subjectName);

}
