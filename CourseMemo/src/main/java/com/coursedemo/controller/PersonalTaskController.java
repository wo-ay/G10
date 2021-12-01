package com.coursedemo.controller;

import com.coursedemo.model.PersonalTask;
import com.coursedemo.util.DBUtil;
import sun.security.x509.InvalidityDateExtension;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

public class PersonalTaskController {

    public static PersonalTask nextTask = null;

    public void addPersonalTask() {
        //对输入的内容进行判断是否合法
        //？？？？？还没写
        int personal_task_id = -1;
        String personal_task_title = "";
        String personal_task_content = "";
        String user_id = "";
        int course_id = -1;
        Date time = null;
        boolean isfinished = false;
        //合理就插入
        Connection conn = null;
        java.sql.PreparedStatement pst = null;
        java.sql.ResultSet rs = null;
        String sql = "";
        try {
            DBUtil.getConnection();
            //获取personal_task_id
            sql = "select max(personal_task_id) from personal_task";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                personal_task_id = rs.getInt(1) + 1;
            } else {
                personal_task_id = 1;
            }

            //插入
            sql = "insert personal_task values(?,?,?,?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, personal_task_id);
            pst.setString(2, personal_task_title);
            pst.setString(3, personal_task_content);
            pst.setString(4, user_id);
            pst.setInt(5, course_id);
            pst.setTimestamp(6, new Timestamp(time.getTime()));
            pst.setString(7, String.valueOf(isfinished));
            pst.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //获取未完成的个人任务
    public List<PersonalTask> loadPersonalTask() {
        String user_id = "31901245";
        List<PersonalTask> res = new ArrayList<>();
        Connection conn = null;
        java.sql.PreparedStatement pst = null;
        java.sql.ResultSet rs = null;
        String sql = "";
        try {
            conn = DBUtil.getConnection();
            sql = "select * from personal_task where user_id = ? and isfinished = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, user_id);
            pst.setString(2, "false");
            rs = pst.executeQuery();
            while (rs.next()) {
                PersonalTask p = new PersonalTask();
                p.setPersonal_task_id(rs.getInt(1));
                p.setPersonal_task_title(rs.getString(2));
                p.setPersonal_task_content(rs.getString(3));
                p.setUser_id(rs.getString(4));
                p.setCourse_id(rs.getInt(5));
                p.setTime(new Date(rs.getTimestamp(6).getTime()));
                p.setIsfinished(Boolean.valueOf(rs.getString(7)));
                res.add(p);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //对list进行排序，按任务发生先后顺序
        Collections.sort(res, new Comparator<PersonalTask>() {
            @Override
            public int compare(PersonalTask o1, PersonalTask o2) {
                if (o1.getTime().compareTo(o2.getTime()) > 0) {//o1 > o2
                    return 1;
                } else if (o1.getTime().compareTo(o2.getTime()) < 0) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        return res;
    }

    public void deletePersonalTask(PersonalTask personalTask) {
        Connection conn = null;
        java.sql.PreparedStatement pst = null;
        java.sql.ResultSet rs = null;
        String sql = null;
        try {
            conn = DBUtil.getConnection();
            sql = "delete from personal_task where personal_task_id = ?";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, personalTask.getPersonal_task_id());
            pst.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void timerReminded() {
        Thread thread = new Thread(new MyPersonalTaskThread());
        thread.start();
    }

    public void returnTask(PersonalTask task) {
        //返回关于下一个需要提示的任务的一个描述
        System.out.println(task.getPersonal_task_title());
        PersonalTaskController.nextTask = null;
    }

    public static void main(String[] args) {
        new PersonalTaskController().timerReminded();
    }
}

//class CourseTimerTask extends TimerTask {
//    @Override
//    public void run() {
//        new PersonalTaskController().returnTask(PersonalTaskController.nextTask);
//    }
//}

class MyPersonalTaskThread implements Runnable {

    @Override
    public void run() {

        //一直循环获取下一个需要提醒个人任务
        while (true) {

            //获取用户的未完成的个人任务列表
            List<PersonalTask> taskList = new PersonalTaskController().loadPersonalTask();
//            for (int i = 0; i < taskList.size(); i++) {
//                System.out.println(taskList.get(i).getPersonal_task_title() + taskList.get(i).getTime().toString());
//            }

            //若没有则continue
            if (taskList.size() == 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("sleep 1s");
                continue;
            }

            //获取最近的（时间属性还没到），有时间属性的一个个人任务，为其设置定时器
            for (int i = 0; i < taskList.size(); i++) {
                if (taskList.get(i).getTime() != null) {
                    long now = new Date().getTime();
                    long thisTime = taskList.get(i).getTime().getTime();
                    if (thisTime > now) {//如果那件事情还没发生
                        PersonalTaskController.nextTask = taskList.get(i);
                        break;
                    }
                }
            }


            if (PersonalTaskController.nextTask != null) {
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        new PersonalTaskController().returnTask(PersonalTaskController.nextTask);
                    }
                }, PersonalTaskController.nextTask.getTime());//什么时间去执行什么事情
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
