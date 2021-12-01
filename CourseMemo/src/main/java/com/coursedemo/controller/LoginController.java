package com.coursedemo.controller;

import com.coursedemo.model.Student;
import com.coursedemo.util.DBUtil;
import com.coursedemo.util.ServerResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

@CrossOrigin
@Controller
@ResponseBody
public class LoginController {
    public static void main(String[] args) {
        //new LoginController().student_login();
    }

//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    @ResponseBody
//    public ServerResponse login(@RequestBody(required = false) Map<String, String> params) {
//
//        String identity = params.get("identity");
//        String id = params.get("id");
//        String pwd = params.get("pwd");
//        //System.out.println(identity + " " + id + " " + pwd);
//        if ("".equals(id) == true || id == null) System.out.println("请输入账号");
//        if ("".equals(pwd) == true || pwd == null) System.out.println("请输入密码");
//        Connection conn = null;
//        if (identity.equals("学生")) {
//            try {
//                conn = DBUtil.getConnection();
//                String sql ="select password from student where id = ?";
//                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
//                pst.setString(1, id);
//                java.sql.ResultSet rs = pst.executeQuery();
//                if (rs.next()) {
//                    String pwdExisted = rs.getString(1);
//                    if (pwd.equals(pwdExisted) == true) {
//                        //密码对上了,查询该学生的信息
//                        String sqlRes = "select * from student where id = ?";
//                        pst = conn.prepareStatement(sqlRes);
//                        pst.setString(1, id);
//                        rs = pst.executeQuery();
//                        Student s = new Student();
//                        if (rs.next()) {
//                            s.setId(id);
//                            s.setName(rs.getString(2));
//                            s.setPassword(rs.getString(3));
//                            s.setPhone(rs.getString(4));
//                            s.setEmail(rs.getString(5));
//                        }
//                        //0-成功，1-失败
//                        System.out.println(id + "登陆成功");
//                        return ServerResponse.createBySuccess("登录成功", s);
//
//                    } else {
//                        System.out.println(id + "密码错误");
//                        return new ServerResponse(1, null, "密码错误");
//                    }
//                } else {
//                    System.out.println(id + "不存在");
//                    return new ServerResponse(1, null, "用户名不存在");
//                }
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//        return new ServerResponse(1, null, "未知错误");
//    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse login(@RequestParam("identity") String identity, @RequestParam("id") String id, @RequestParam("pwd") String pwd) {
        if ("".equals(id) == true || id == null) System.out.println("请输入账号");
        if ("".equals(pwd) == true || pwd == null) System.out.println("请输入密码");
        Connection conn = null;
        if (identity.equals("学生")) {
            try {
                conn = DBUtil.getConnection();
                String sql ="select password from student where id = ?";
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, id);
                java.sql.ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    String pwdExisted = rs.getString(1);
                    if (pwd.equals(pwdExisted) == true) {
                        //密码对上了,查询该学生的信息
                        String sqlRes = "select * from student where id = ?";
                        pst = conn.prepareStatement(sqlRes);
                        pst.setString(1, id);
                        rs = pst.executeQuery();
                        Student s = new Student();
                        if (rs.next()) {
                            s.setId(id);
                            s.setName(rs.getString(2));
                            s.setPassword(rs.getString(3));
                            s.setPhone(rs.getString(4));
                            s.setEmail(rs.getString(5));
                        }
                        //0-成功，1-失败
                        System.out.println(id + "登陆成功");
                        return ServerResponse.createBySuccess("登录成功", s);
                    } else {
                        System.out.println(id + "密码错误");
                        return new ServerResponse(1, null, "密码错误");
                    }
                } else {
                    System.out.println(id + "不存在");
                    return new ServerResponse(1, null, "用户名不存在");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return new ServerResponse(1, null, "未知错误");
    }
}
