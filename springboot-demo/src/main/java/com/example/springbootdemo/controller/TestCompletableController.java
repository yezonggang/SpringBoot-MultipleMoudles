package com.example.springbootdemo.controller;


import com.example.springbootdemo.model.entitiy.Student;
import com.example.springbootdemo.model.entitiy.Teacher;
import com.example.springbootdemo.model.reponse.ResponseData;
import com.example.springbootdemo.service.TeacherServiceI2mpl;
import com.example.springbootdemo.service.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import static java.util.concurrent.CompletableFuture.completedFuture;

/**
 * Hello world!
 *
 */

public class TestCompletableController {

    @Autowired
    static TeacherServiceImpl teacherService;

    @Autowired
    static TeacherServiceI2mpl teacherService2;

    public void testxx() {
        System.out.println("begin.");
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
        Teacher t1 = new Teacher(1, "12", "boy", "laoshi", studentList);

        // map直接取数
        List<Integer> list = t1.getStudentList()
                .stream()
                .map(Student::getId)
                .collect(Collectors.toList());
        List<Integer> list1 = t1.getStudentList()
                .stream()
                .map(student -> student.getId())
                .collect(Collectors.toList());
        System.out.println(list);
        System.out.println(list1);
        // map做运算
        List<Integer> list2 = t1.getStudentList()
                .stream()
                .map(student -> {
                    return student.getId() + 1;
                }).collect(Collectors.toList());
        System.out.println("list2：" + list2);
        // filter过滤
        List<Student> list3 = t1.getStudentList()
                .stream()
                .filter(student -> {
                    return student.getId() > 3;
                }).filter(student -> {
                    return student.getName().contains("s");
                }).collect(Collectors.toList());
        System.out.println("list3:" + list3);
        List<Student> list4 = t1.getStudentList()
                .stream()
                .filter(student -> student.getId() > 2)
                .collect(Collectors.toList());

        // flatmap拍扁,输出值
        BinaryOperator<Student> getBiggestID = (x1, x2) -> {
            int id1 = x1.getId();
            int id2 = x2.getId();
            return x1.getId() > x2.getId() ? x1 : x2;

        };
        Integer maxID = Optional.ofNullable(t1.getStudentList())
                .flatMap(students -> students.stream().reduce(getBiggestID))
                .map(Student::getId)
                .orElse(0);
        System.out.println(maxID);



        // groupby转化
        Map<String, List<Student>> list6 = t1.getStudentList().stream()
                .filter(student -> !student.getName().isEmpty())
                .collect(Collectors.groupingBy(x -> x.getName().contains("s") ? "ss" : "nn"));

        System.out.println(list6);

    }

        // 异步编程链路
    public  CompletableFuture<String> test() throws  Exception{
        return teacherService.getStudents()
                .thenApplyAsync(students -> students.stream()
                .filter(i->i.getName().contains("s"))
                .collect(Collectors.toList()))
                .thenApply(students -> teacherService.getTeachers(students).toString())
                .toCompletableFuture();

    }

    public static CompletableFuture<ResponseData> test2(){
        return teacherService2.getStudents().thenComposeAsync(students->
                students.left.map(l-> completedFuture(ResponseData.from(l)))
        .orElseGet(()->{
            List<Student> studentList=students.right.get();
            if(studentList.size()>0){
                return  completedFuture(ResponseData.success(studentList));
            }else {
                return completedFuture(ResponseData.success(Collections.EMPTY_LIST));
            }
        }));

    }


}


