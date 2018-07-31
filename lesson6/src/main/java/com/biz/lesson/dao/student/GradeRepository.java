package com.biz.lesson.dao.student;

import com.biz.lesson.model.user.Grade;
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
public interface GradeRepository extends Repository<Grade,Integer> {

    public Grade findGradeByGradeId(Integer gradeId);
    public List<Grade> findAll();
    public List<Grade> findGradeByGradeName(String gradeName);

    @Modifying
    @Query("update Grade o set o.gradeName=:name where o.gradeId=:id")
    public void update(@Param("id") Integer id, @Param("name") String name);

//    @Query("select o from Student o where o.name like %?1%\n")
//    public List<Student> queryLike1(String name);

//    @Modifying
//    @Query("update Grade o set o.name=:name where o.id=:id")
//    public void update(@Param("id") Integer id, @Param("name") String name);
}
