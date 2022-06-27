package com.example.springbootdemo.service;

import com.example.springbootdemo.model.entitiy.Student;
import com.example.springbootdemo.model.entitiy.Teacher;
import com.example.springbootdemo.model.reponse.ApiError;
import com.example.springbootdemo.model.reponse.Either;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.completedFuture;


@Service
public class TeacherServiceI2mpl {
    Student a = new Student(1, "sat", "zhangsan", "1");
    Student b = new Student(2, "hhh", "lisi", "2");
    Student c = new Student(3, "kg", "wangwu", "3");
    Student d = new Student(4, "ss", "mazi", "4");
    Student e = new Student(5, "tt", "zhaowu", "5");
    Student f = new Student(6, "dfa", "xuliu", "6");
    Student g = new Student(7, "safg", "dongqi", "7");
    Student h = new Student(8, "tsa", "chenba", "8");


    List<Student> students = new ArrayList<Student>();

    // 假设需要进行数据库查询操作
    public CompletableFuture<Either<ApiError,List<Student>>>  getStudents(){
        Student a = new Student(1, "sat", "zhangsan", "1");
        Student b = new Student(2, "hhh", "lisi", "2");
        Student c = new Student(3, "kg", "wangwu", "3");
        Student d = new Student(4, "ss", "mazi", "4");
        Student e = new Student(5, "tt", "zhaowu", "5");
        Student f = new Student(6, "dfa", "xuliu", "6");
        Student g = new Student(7, "safg", "dongqi", "7");
        Student h = new Student(8, "tsa", "chenba", "8");
        List<Student> studentList = new ArrayList<Student>();
        studentList.add(a);
        studentList.add(b);
        studentList.add(c);
        studentList.add(d);
        studentList.add(e);
        studentList.add(f);
        studentList.add(g);
        studentList.add(h);
        studentList.add(h);
        if(null==studentList){
            return completedFuture(Either.Left(ApiError.from("the list is empty or null.","xxx001")));
        }else{
            return completedFuture(Either.Right(studentList));
        }
    }

    // 对数据库返回值再加工
    public CompletableFuture<List<Teacher>> getTeachers(List<Student> students){
        List<Teacher> teachers = new ArrayList<Teacher>();
        Teacher t2 = new Teacher(1, "12", "boy", "lixx", students);
        Teacher t3 = new Teacher(3, "21", "girl", "wangxx", students);
        teachers.add(t2);
        teachers.add(t3);
        return completedFuture(teachers);
    }


}
