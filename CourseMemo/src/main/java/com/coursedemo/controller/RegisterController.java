package com.coursedemo.controller;

import com.coursedemo.util.DBUtil;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Connection;
import java.sql.SQLException;

public class RegisterController {

    @RequestMapping(value = "/Register")
    public void register() {
        int verify_id = -1;
        String user_id = "";
        String user_identity = "";
        String user_name = "";
        String password = "";
        String user_identitycard = "";
        String phone = "";
        String email = "";
        String verify_status = "未审核";
        Connection conn = null;
        java.sql.PreparedStatement pst = null;
        java.sql.ResultSet rs = null;
        if (user_name == null || "".equals(user_name)) {
            System.out.println("请输入姓名");
        }
        if (password == null || "".equals(password)) {

        }
        if (user_identitycard == null || "".equals(user_identitycard)) {

        }
        if (phone == null || "".equals(phone)) {

        }
        if (email == null || "".equals(email)) {

        }
        try {
            conn = DBUtil.getConnection();
            if (user_identity.equals("教师")) {

                if (user_id == null || "".equals(user_id)) {
                    System.out.println("请输入教工号");
                }
                //在所有信息都填写后不为空，在教师表中查该id有没有被注册
                String confirmSql = "select * from teacher where id = ?";
                pst = conn.prepareStatement(confirmSql);
                pst.setString(1, user_id);
                rs = pst.executeQuery();
                if (rs.next()) {
                    System.out.println("该id已被注册");
                }
                //生成verify_id
                String sql = "select max(verify_id) from verify";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (!rs.next()) {
                    verify_id = 1;
                } else {
                    verify_id = rs.getInt(1) + 1;
                }
                String sql2 = "insert into verify values(?,?,?,?,?,?,?,?,?)";
                //sql = "insert into verify(verify_id) values(?)";
                pst = conn.prepareStatement(sql2);
                pst.setInt(1, verify_id);
                pst.setString(2, user_id);
                pst.setString(3, user_identity);
                pst.setString(4, user_name);
                pst.setString(5, password);
                pst.setString(6, user_identitycard);
                pst.setString(7, phone);
                pst.setString(8, email);
                pst.setString(9, verify_status);
                pst.execute();
                System.out.println("请等待审核");
            } else if (user_identity.equals("学生")){
                if (user_id == null || "".equals(user_id)) {
                    System.out.println("请输入学号");
                }
                //在所有信息都填写后不为空，在教师表中查该id有没有被注册
                String confirmSql = "select * from student where id = ?";
                pst = conn.prepareStatement(confirmSql);
                pst.setString(1, user_id);
                rs = pst.executeQuery();
                if (rs.next()) {
                    System.out.println("该id已被注册");
                }
                //生成verify_id
                String sql = "select max(verify_id) from verify";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (!rs.next()) {
                    verify_id = 1;
                } else {
                    verify_id = rs.getInt(1) + 1;
                }
                String sql2 = "insert into verify values(?,?,?,?,?,?,?,?,?)";
                //sql = "insert into verify(verify_id) values(?)";
                pst = conn.prepareStatement(sql2);
                pst.setInt(1, verify_id);
                pst.setString(2, user_id);
                pst.setString(3, user_identity);
                pst.setString(4, user_name);
                pst.setString(5, password);
                pst.setString(6, user_identitycard);
                pst.setString(7, phone);
                pst.setString(8, email);
                pst.setString(9, verify_status);
                pst.execute();
                System.out.println("请等待审核");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
