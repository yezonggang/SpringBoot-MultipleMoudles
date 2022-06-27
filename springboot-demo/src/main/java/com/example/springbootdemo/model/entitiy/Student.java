package com.example.springbootdemo.model.entitiy;


import java.util.List;

public class Student {

    public Student(int id, String name, String gender, String age) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public Student(int id, String name, String gender, String age, List<String> harbor) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.harbor = harbor;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age='" + age + '\'' +
                ", harbor=" + harbor +
                '}';
    }

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    int id;
    String name;
    String gender;
    String age;
    List<String> harbor;
}
