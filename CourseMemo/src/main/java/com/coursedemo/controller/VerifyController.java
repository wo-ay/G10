package com.coursedemo.controller;

import com.coursedemo.model.Verify;
import com.coursedemo.util.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VerifyController {

    public void passVerify(Verify verify) {
        Connection conn = null;
        java.sql.PreparedStatement pst = null;
        java.sql.ResultSet rs = null;
        String sql = "";
        try {
            conn = DBUtil.getConnection();
            sql = "update verify set verify_status = ? where verify_id = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, "通过");
            pst.setInt(2, verify.getVerify_id());
            pst.execute();
            if (verify.getUser_identity().equals("教师") ) {
                sql = "insert teacher values(?,?,?,?,?)";
            } else if (verify.getUser_identity().equals("学生") ) {
                sql = "insert student values(?,?,?,?,?)";
            }
            pst = conn.prepareStatement(sql);
            pst.setString(1, verify.getUser_id());
            pst.setString(2, verify.getUser_name());
            pst.setString(3, verify.getPassword());
            pst.setString(4, verify.getPhone());
            pst.setString(5, verify.getEmail());
            pst.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void rejectVerify(Verify verify) {
        Connection conn = null;
        java.sql.PreparedStatement pst = null;
        java.sql.ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "update verify set verify_status = ? where verify_id = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, "驳回");
            pst.setInt(2, verify.getVerify_id());
            pst.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Verify> loadVerify() {
        List<Verify> res = new ArrayList<>();
        Connection conn = null;
        java.sql.PreparedStatement pst = null;
        java.sql.ResultSet rs = null;
        String sql = "";
        try {
            conn = DBUtil.getConnection();
            sql = "select * from verify where verify_status = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1,"未审核");
            rs = pst.executeQuery();
            while (rs.next()) {
                Verify verify = new Verify();
                verify.setVerify_id(rs.getInt(1));
                verify.setUser_id(rs.getString(2));
                verify.setUser_identity(rs.getString(3));
                verify.setUser_name(rs.getString(4));
                verify.setPassword(rs.getString(5));
                verify.setUser_identitycard(rs.getString(6));
                verify.setPhone(rs.getString(7));
                verify.setEmail(rs.getString(8));
                verify.setVerify_status(rs.getString(9));
                res.add(verify);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res;
    }

}
