package com.example.user.guruforstudent.Models;

import com.example.user.guruforstudent.MyConnection;
import com.google.firebase.auth.FirebaseAuth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by USER on 9/6/2018.
 */


import android.content.Intent;
import android.provider.Settings;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
// Import Java package for File I/O
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
     * Created by USER on 8/14/2018.
     */

    public class User {

        static Connection con = null;
        static PreparedStatement ps = null;
        String email;
        String passwd;
        int curUlevel; //current user Level
        int curUid; //current user ID
        FirebaseAuth auth;
        String firstname;
        String lastname;

        public User() {
            con = MyConnection.getconnection();

        }

        public int UserLogin(String email, String passwd) {

            this.email = email;
            this.passwd = passwd;
            try {
                ps = con.prepareStatement("SELECT * FROM `users` WHERE `email`=? AND `password`=?");
                ps.setString(1, this.email);
                ps.setString(2, this.passwd);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    System.out.print("Login Sucess");
                    //con.close();
                    return 1;

                } else {
                    System.out.print("Login fail");
                    //con.close();
                    return 0;
                }
                //rs.close();
                //ps.close();
                 //con.close();

            } catch (Exception e) {
                System.out.print(e);
                return 2;
            }


        }

        public static PreparedStatement UserReg(String firstname, String lastname, String email, String password,int roleid)  {
            con = MyConnection.getconnection();
            String query = "INSERT INTO `users`( `firstname`, `Lastname`, `email`, `password`, `role_id`) VALUES (?,?,?,?,?)";
            try {
                ps = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, firstname);
                ps.setString(2, lastname);
                ps.setString(3, email);
                ps.setString(4, password);
                ps.setInt(5, roleid);
                //ps.close();
                //con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }




            return ps;

        }
        public int getCurIdCurLevel(){ //get the user level
            int ulevel=0;
            auth = FirebaseAuth.getInstance();
            String mail = auth.getCurrentUser().getEmail();
            try {
                ps = con.prepareStatement("SELECT `id`, `role_id` FROM `users` WHERE `email`=?");
                ps.setString(1, mail);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    this.curUid = rs.getInt(1);
                    ulevel = rs.getInt(2);
                    this.curUlevel = ulevel;

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return ulevel;
        }

        //con.close();
        public static void conClose(){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    public List<String> getUserInfo(){ //get the user information
        List<String> userinfolist = new ArrayList<String>();
        auth = FirebaseAuth.getInstance();
        String mail = auth.getCurrentUser().getEmail();
        try {
            ps = con.prepareStatement("SELECT `firstname`, `Lastname`, `email` FROM `users` WHERE `email` = ?");
            ps.setString(1, mail);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String fname = rs.getString(1);
                String lname = rs.getString(2);
                String uname = rs.getString(3);
                userinfolist.add(fname);
                userinfolist.add(lname);
                userinfolist.add(uname);



            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userinfolist;
    }

    public int getCurUserId(){ //get the user level
        int uid =0;
        auth = FirebaseAuth.getInstance();
        String mail = auth.getCurrentUser().getEmail();
        try {
            ps = con.prepareStatement("SELECT `id` FROM `users` WHERE `email`=?");
            ps.setString(1, mail);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                uid = rs.getInt(1);


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return uid;
    }

    }


