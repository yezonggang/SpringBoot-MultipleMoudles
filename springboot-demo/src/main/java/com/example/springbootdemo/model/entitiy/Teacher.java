package com.example.springbootdemo.model.entitiy;

import java.util.List;


public class Teacher {
    public Teacher(int id, String age, String gender, String name, List<Student> studentList) {
        this.id = id;
        this.age = age;
        this.gender = gender;
        this.name = name;
        this.studentList = studentList;
    }

    int id;
    String age;
    String gender;
    String name;
    List<Student> studentList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
