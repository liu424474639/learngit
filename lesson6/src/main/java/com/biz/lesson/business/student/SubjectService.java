package com.biz.lesson.business.student;

import com.biz.lesson.dao.student.SubjectCrudRepository;
import com.biz.lesson.dao.student.SubjectRepository;
import com.biz.lesson.model.user.Student;
import com.biz.lesson.model.user.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

/**
 * Created by lyx on 2018/7/29.
 */
@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private SubjectCrudRepository subjectCrudRepository;

    public void saveSubject(Subject subject){

        subjectCrudRepository.save(subject);
    }

    public Subject findSubject(Integer subjectId){
        Subject subject = subjectCrudRepository.findOne(subjectId);
        return subject;
    }

    public Subject findSubject(String subjectName) {
        Subject subject = subjectRepository.findSubject(subjectName);
        return subject;
    }

    public void saveAll(List<Subject> subjects) {
        subjectCrudRepository.save(subjects);
    }

   public void deleteSubject(Integer subjectId){
       subjectCrudRepository.delete(subjectId);
   }

   public void update(Integer subjectId, Integer subjectNumber){
       subjectRepository.updateSubjectNumber(subjectId,subjectNumber);
   }

   public void updateSubject(Integer subjectId,String subjectName,Integer subjectNumber,Integer subjectAverage){
       subjectRepository.update(subjectId,subjectName,subjectNumber,subjectAverage);
   }

   //更新学科的平均分
   public void updateSubjectAverage(String subjectName, Integer newScore){

           int count = 0;
           int total = 0;
           int average;
           Subject subject = subjectRepository.findSubject(subjectName);
           System.out.println("学科为：" + subject.toString());
           Iterator<Student> it = subject.getStudents().iterator();
           while(it.hasNext()){
               System.out.println("在" + subject.getSubjectName() + "的是：");
               Student student = it.next();
               System.out.println("名字是");
               System.out.println(subject.getSubjectName());
               System.out.println("分数是");
               if (student.getAverage()!=null){
                   average = subject.getSubjectAverage();
               }else {
                   average = 0;
               }
               System.out.println(subject.getSubjectAverage());
               count = count + 1;
               total = total + average;
           }
           int subjectNumber = subject.getSubjectNumber();
           average = (total+newScore)/(subjectNumber);
           System.out.println("总共几个人：" + subjectNumber);
           System.out.println("总分数：" + total);
           System.out.println("学科平均分为：" + average);
           subjectRepository.updateSubjectAverage(subjectName,average);
//       Subject subject = subjectRepository.findSubject(subjectName);
//       System.out.println("学科信息：" + subject.toString());
//       int subjectAverage;
//       int subjectNumber;
//       if (subject.getSubjectAverage() != null){
//           subjectAverage = subject.getSubjectAverage();
//       }else {
//           subjectAverage = 0;
//       }
//       System.out.println("原来的平均分" + subjectAverage);
//
//       if (subject.getSubjectNumber() != null){
//           subjectNumber = subject.getSubjectNumber();
//       }else{
//           subjectNumber = 0;
//       }
//       System.out.println("当前人数为：" + subjectNumber);
//       subjectAverage = (subjectAverage*(subjectNumber-1) + newScore)/(subjectNumber);
//       subjectRepository.updateSubjectAverage(subjectName,subjectAverage);
//   }
//
//    //查找学科的平均分
//    public Integer findSubjectAverage(String subjectName) {
//        int count = 0;
//        int total = 0;
//        int average = 0;
//        Subject subject = subjectRepository.findSubject(subjectName);
//        System.out.println("班级为：" + subject.toString());
//        Iterator<Student> it = subject.getStudents().iterator();
//        while(it.hasNext()){
//            System.out.println("在" + subject.getSubjectName() + "的是：");
//            Student student = it.next();
//            System.out.println("名字是");
//            System.out.println(student.getName());
//            System.out.println("分数是");
//            if (student.getAverage()!=null){
//                average = student.getAverage();
//            }
//
//            System.out.println(student.getAverage());
//            count = count + 1;
//            total = total + average;
//        }
//        average = total/count;
//        System.out.println("总共几个人：" + count);
//        System.out.println("总分数：" + total);
//        System.out.println("学科平均分为：" + average);
//        return average;
//    }
//
//    //查找学科的人数
//    public Integer findSubjectNumber(String subjectName) {
//        int subjectNumber = 0;
//        Subject subject = subjectRepository.findSubject(subjectName);
//        Iterator<Student> it = subject.getStudents().iterator();
//        while(it.hasNext()){
//            System.out.println("在" + subject.getSubjectName() + "的是：");
//            Student student = it.next();
//            System.out.println("名字是");
//            System.out.println(student.getName());
//            subjectNumber = subjectNumber + 1;
//        }
//        System.out.println("总共几个人：" + subjectNumber);
//        //更新学科人数
////        subjectNumber = subjectNumber;
////        subjectRepository.updateSubjectNumber(subject.getSubjectId(),subjectNumber);
//        return subjectNumber;
//    }
//
//    public Subject findSubject(String subjectName) {
//       Subject subject = subjectRepository.findSubject(subjectName);
//       return subject;
    }


    public Integer findSubjectNumber(String subjectName) {
       Subject subject = subjectRepository.findSubject(subjectName);
       int subjectNumber = subject.getSubjectNumber();
       return subjectNumber;
    }
}
