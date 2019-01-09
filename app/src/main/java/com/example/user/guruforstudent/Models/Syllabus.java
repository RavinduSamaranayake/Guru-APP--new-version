package com.example.user.guruforstudent.Models;

import com.example.user.guruforstudent.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Syllabus {
    String id;
    String name;
    Connection con = null;
    PreparedStatement ps;
    public Syllabus(){
        con = MyConnection.getconnection();
    }

    public List<List<String>> getAllsyllabus(int insid,int crsid){
        List<List<String>> syllabusName = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT module.`name`,module.`description`,module.`learning_points` FROM `course_institutes` crsins ,`modules` module WHERE crsins.`institute_id`= ? AND crsins.`course_id` = ? AND crsins.syllabus_id = module.`syllabus_id` ";


        try {
            ps = con.prepareStatement(selectQuery);
            ps.setInt(1,insid);
            ps.setInt(2,crsid);

            ResultSet rs = ps.executeQuery();
            //insName.add("Hello");

            while(rs.next()){
                List<String> syllabus = new ArrayList<String>(); //to get the name,description,lpoint separately
                String name = rs.getString(1);
                String desc = rs.getString(2);
                String l_points = rs.getString(3);
                syllabus.add(name);
                syllabus.add(desc);
                syllabus.add(l_points);
                syllabusName.add(syllabus);
            }


        } catch (SQLException e) {
            //Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            System.out.print(e.getMessage());
        }

        return  syllabusName;
    }




    public int getsyllabusId(String name){
        int ID = 0;
        String selectQuery = "SELECT `id` FROM `modules` WHERE `name` = ?";


        try {
            ps = con.prepareStatement(selectQuery);
            ps.setString(1,name);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                ID = rs.getInt(1);

            }
            //con.close();

        } catch (SQLException e) {
            //Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            System.out.print(e.getMessage());
        }






        return ID;
    }


}
