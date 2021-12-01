package com.coursedemo.model;

public class Student {
    private String id;
    private String name;
    private String password;
    private String gender;
    private String phone;
    private String email;

    public Student() {
    }

    public Student(String id, String name, String password, String gender, String phone, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
