package com.skillbox.mongodemo;

public class Student {
    private String name;
    private String courses;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourses() {
        return courses;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student(String name, int year, String author){
        this.courses = author;
        this.name = name;
        this.age = year;


    }
}
