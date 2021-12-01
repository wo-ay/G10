package com.coursedemo.util;

import java.sql.Connection;

public class DBUtil {
    private static final String jdbcUrl="jdbc:mysql://localhost:3306/coursememo?useUnicode=true&characterEncoding=UTF-8";
    private static final String dbUser="root";
    private static final String dbPwd="123456";

    //Class.forName() 方法要求JVM查找并加载指定的类到内存中，此时将"com.mysql.jdbc.Driver" 当做参数传入，就是告诉JVM
    // 去"com.mysql.jdbc"这个路径下找Driver类，将其加载到内存中。
    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws java.sql.SQLException{
        Connection conn = java.sql.DriverManager.getConnection(jdbcUrl, dbUser, dbPwd);
        return conn;
    }
}
