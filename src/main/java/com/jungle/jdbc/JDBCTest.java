package com.jungle.jdbc;

import java.sql.*;
import java.util.Enumeration;

/**
 * Created by yunjiang on 2017/1/13.
 */
public class JDBCTest {
    static {
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }
    public static void main(String[] args){
test();
    }

    public static void test(){
        final String driver = "com.mysql.jdbc.Driver";
        final String uri = "jdbc:mysql://localhost:3306/bip_center_v1";
        final String user = "root";
        final String password = "root";
        String sql = "select count(1) from sys_users";
        Connection conn = null;

        System.out.println(System.getProperty("jdbc.drivers"));
        try {
            conn = DriverManager.getConnection(uri, user,
                    password);
            Connection conn1 =  DriverManager.getConnection(uri, user,
                    password);
            Connection conn2 =  DriverManager.getConnection(uri, user,
                    password);
            Enumeration<Driver> driverEnumeration = DriverManager.getDrivers();
            while (driverEnumeration.hasMoreElements()){
                Driver tmpDriver = driverEnumeration.nextElement();
                System.out.println(tmpDriver);
                //tmpDriver.getPropertyInfo();
            }
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("count : " + rs.getInt(1));
            }
            conn.close();
            conn = DriverManager.getConnection(uri,user,password);
            sql ="select id from sys_users limit 1";
            ps = conn1.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("count : " + rs.getInt(1));
            }
        }catch (Exception e){
e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
