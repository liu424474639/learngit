package com.biz.lesson.dao.student;

import com.biz.lesson.model.user.Student;
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
public interface StudentRepository extends Repository<Student,Integer> {

    public List<Student> findByName(String name);
    public Student findById(Integer id);
    public List<Student> findByBirthday(String birthday);
    public List<Student> findAll();
    Student findByIdAndNameAndBirthday(Integer id, String name, String birthday);
    public Student save(Student student);

    @Query("select o from Student o where o.id=(select max(o.id) from Student t1)")
    public Student getStudentByMaxId();

    @Query("select o from Student o where o.name=?1 and o.sex=?2")
    public List<Student> queryParam1(String name, String sex);

    @Query("select o from Student o where o.name=:name and o.sex=:sex")
    public List<Student> queryParam2(@Param("name") String name, @Param("sex") String sex);

//    @Query("select o from Student o where o.name like %?1%\n")
//    public List<Student> queryLike1(String name);

    @Modifying
    @Query("update Student o set o.courseNumber=:courseNumber where o.id=:id")
    public void update(@Param("id") Integer id, @Param("courseNumber") Integer courseNumber);

    @Modifying
    @Query("update Student o set  o.average=:average where o.id=:id")
    public void updateAverage(@Param("id") Integer id,@Param("average") Integer average);

    @Modifying
    @Query("update Student o set o.StudentId=:studentId," +
            "o.name=:name," +
            "o.sex=:sex," +
            "o.birthday=:birthday," +
            "o.grade=:grade," +
            "o.courseNumber=:courseNumber," +
            "o.average=:average where o.id=:id")
    public void updateAll(@Param("id") Integer id, @Param("name") String name,
                          @Param("sex") String sex, @Param("studentId") String studentId,
                          @Param("birthday") String birthday,@Param("grade") String grade,
                          @Param("courseNumber") Integer courseNumber, @Param("average") Integer average);

}
