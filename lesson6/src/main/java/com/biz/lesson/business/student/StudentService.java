package com.biz.lesson.business.student;

import com.biz.lesson.dao.student.StudentCrudRepository;
import com.biz.lesson.dao.student.StudentRepository;
import com.biz.lesson.model.user.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lyx on 2018/7/28.
 */
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentCrudRepository studentCrudRepository;

    @Transactional
    public void update(Integer id, String name){
        studentRepository.update(id,name);
    }

//    保存所有对象需要用saveAll
    public void saveAll(List<Student> students){
        studentCrudRepository.save(students);
    }

    public void save(Student student){
        studentCrudRepository.save(student);
    }

    public void deleteById(Integer id) {
        studentCrudRepository.delete(id);
    }

    public void deleteAll(){
        studentCrudRepository.deleteAll();
    }
}
