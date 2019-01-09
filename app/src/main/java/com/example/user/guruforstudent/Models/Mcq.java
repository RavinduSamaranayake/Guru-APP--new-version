package com.example.user.guruforstudent.Models;

import com.example.user.guruforstudent.MyConnection;
import com.example.user.guruforstudent.addMcq;
import com.example.user.guruforstudent.userRegister;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Mcq {
    Connection con = null;
    PreparedStatement ps = null;
    PreparedStatement ps1 = null;
    PreparedStatement ps2 = null;
    public Mcq(){
        con = MyConnection.getconnection();
    }


    public PreparedStatement AddMcq(int syllabusId,String description,int marks){  //Fill Mcq Table
        String query = "INSERT INTO `mcqs`(`paper_id`, `description`, `marks`) VALUES (?,?,?)";
        try {
            ps1 = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            ps1.setInt(1,syllabusId); // to get the last index of mcq table
            ps1.setString(2,description);
            ps1.setInt(3,marks);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps1;
    }


    public PreparedStatement AddMcqAswers(int mcqid,String choice,int status){  //Fill Mcq Answer Table
        String query = "INSERT INTO `mcq_answers`(`mcq_id`, `answer`, `status`) VALUES (?,?,?)";
        try {

            ps2 = con.prepareStatement(query);
            ps2.setInt(1,mcqid);
            ps2.setString(2,choice);
            ps2.setInt(3,status);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps2;
    }


    public int getmcqId() { // for get the last index of mcq table
        int id =0;
        PreparedStatement ps = addMcq.getmyps();

        try {
            if (ps.executeUpdate() > 0) {

                ResultSet rs = ps.getGeneratedKeys();

                if (rs.next()) {
                    id = rs.getInt(1);

                }
                //con.close();

            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }

        return id;

    }

}

