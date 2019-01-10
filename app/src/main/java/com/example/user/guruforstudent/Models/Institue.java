package com.example.user.guruforstudent.Models;

import android.widget.Toast;

import com.example.user.guruforstudent.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 9/2/2018.
 */

public class Institue {
    String id;
    String name;
    Connection con = null;
    PreparedStatement ps;
    public Institue(){
        con = MyConnection.getconnection();
    }

    public List<String> getAllInstitutes(){
        List<String> insName = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT `name` FROM `institutes`";


        try {

            ps = con.prepareStatement(selectQuery);
            ResultSet rs = ps.executeQuery();
            //insName.add("Hello");

            while(rs.next()){
                insName.add(rs.getString(1).toString());

            }
            //con.close();

        } catch (SQLException e) {
            //Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            System.out.print(e.getMessage());
        }






        return  insName;
    }
    public int getInsId(String name) {  //
        int insID = 0;
        String selectQuery = "SELECT `id` FROM `institutes` WHERE `name`=?";


        try {
            ps = con.prepareStatement(selectQuery);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            //insName.add("Hello");

            if (rs.next()) {
                insID = rs.getInt(1);

            }
            //con.close();

        } catch (SQLException e) {
            //Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            System.out.print(e.getMessage());
        }

        return  insID;
    }

        public PreparedStatement fillInsRateTable(String comment,int numstars,int stdid,int insId){
            try {
                ps = con.prepareStatement("INSERT INTO `reviews`( `comment`, `stars`, `student_id`, `institute_id`, `status`, `up`, `down`) VALUES (?,?,?,?,?,?,?)");

                ps.setString(1,comment);
                ps.setInt(2,numstars);
                ps.setInt(3,stdid);
                ps.setInt(4,insId);
                ps.setInt(5,0);
                ps.setInt(6,0);
                ps.setInt(7,0);

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return ps;
        }
       public List<String> getComments(int insid){
        List<String> commentslist = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT `comment` FROM `reviews` WHERE `institute_id` = ?";


        try {
            ps = con.prepareStatement(selectQuery);
            ps.setInt(1,insid);


            ResultSet rs = ps.executeQuery();
            //insName.add("Hello");

            while(rs.next()){
                List<String> syllabus = new ArrayList<String>(); //to get the name,description,lpoint separately
                String comment = rs.getString(1);
                commentslist.add(comment);
            }


        } catch (SQLException e) {
            //Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            System.out.print(e.getMessage());
        }

        return  commentslist;
    }




}

