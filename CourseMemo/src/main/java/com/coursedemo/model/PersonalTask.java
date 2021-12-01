package com.coursedemo.model;

import java.util.Date;

public class PersonalTask {
    private int personal_task_id;
    private String personal_task_title;
    private String personal_task_content;
    private String user_id;
    private int course_id;
    private Date time;
    private Boolean isfinished = false;

    public PersonalTask() {
    }

    public PersonalTask(int personal_task_id, String personal_task_title, String personal_task_content, String user_id, int course_id, Date time, Boolean isfinished) {
        this.personal_task_id = personal_task_id;
        this.personal_task_title = personal_task_title;
        this.personal_task_content = personal_task_content;
        this.user_id = user_id;
        this.course_id = course_id;
        this.time = time;
        this.isfinished = isfinished;
    }

    public int getPersonal_task_id() {
        return personal_task_id;
    }

    public void setPersonal_task_id(int personal_task_id) {
        this.personal_task_id = personal_task_id;
    }

    public String getPersonal_task_title() {
        return personal_task_title;
    }

    public void setPersonal_task_title(String personal_task_title) {
        this.personal_task_title = personal_task_title;
    }

    public String getPersonal_task_content() {
        return personal_task_content;
    }

    public void setPersonal_task_content(String personal_task_content) {
        this.personal_task_content = personal_task_content;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Boolean getIsfinished() {
        return isfinished;
    }

    public void setIsfinished(Boolean isfinished) {
        this.isfinished = isfinished;
    }
}
