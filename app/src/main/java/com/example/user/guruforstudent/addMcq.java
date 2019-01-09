package com.example.user.guruforstudent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.guruforstudent.Models.Mcq;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class addMcq extends AppCompatActivity {
    EditText mcqDescript;
    EditText choice1;
    EditText choice2;
    EditText choice3;
    EditText choice4;
    EditText s1;
    EditText s2;
    EditText s3;
    EditText s4;
    EditText correct;
    EditText marks;
    Button create;
    Mcq mcq;
    int syllabusId;
    static PreparedStatement myps;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mcq);
        mcq = new Mcq();
        mcqDescript = findViewById(R.id.mcqdes);
        choice1 = findViewById(R.id.choice1);
        choice2 = findViewById(R.id.choice2);
        choice3 = findViewById(R.id.choice3);
        choice4 = findViewById(R.id.choice4);
        correct = findViewById(R.id.correctans);
        marks = findViewById(R.id.marks);
        create = findViewById(R.id.btncreate);

        syllabusId = Integer.parseInt(getIntent().getStringExtra("syllabusId"));
        marks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mcqdes = mcqDescript.getText().toString();
                String ch1 = choice1.getText().toString();
                String ch2 = choice2.getText().toString();
                String ch3 = choice3.getText().toString();
                String ch4 = choice4.getText().toString();
                int a1 = Integer.parseInt(s1.getText().toString());
                int a2 = Integer.parseInt(s2.getText().toString());
                int a3 = Integer.parseInt(s3.getText().toString());
                int a4 = Integer.parseInt(s4.getText().toString());
                int mark = Integer.parseInt(marks.getText().toString());

                //form validation
                if(mcqdes.length() == 0){
                    Toast.makeText(getApplicationContext(), "You have not created MCQ", Toast.LENGTH_LONG).show();
                }
                else if(ch1.length() == 0){
                    Toast.makeText(getApplicationContext(), "You have not created Choice1", Toast.LENGTH_LONG).show();
                }
                else if(ch2.length() == 0){
                    Toast.makeText(getApplicationContext(), "You have not created Choice2", Toast.LENGTH_LONG).show();
                }
                else if(ch3.length() == 0){
                    Toast.makeText(getApplicationContext(), "You have not created Choice3", Toast.LENGTH_LONG).show();
                }
                else if(ch4.length() == 0){
                    Toast.makeText(getApplicationContext(), "You have not created Choice4", Toast.LENGTH_LONG).show();
                }
                else if((a1 != 0)||(a1 != 1)){
                    Toast.makeText(getApplicationContext(), "Please use valid bit for choices", Toast.LENGTH_LONG).show();
                }
                else if((a2 != 0)||(a2 != 1)){
                    Toast.makeText(getApplicationContext(), "Please use valid bit for choices", Toast.LENGTH_LONG).show();
                }
                else if((a3 != 0)||(a3 != 1)){
                    Toast.makeText(getApplicationContext(), "Please use valid bit for choices", Toast.LENGTH_LONG).show();
                }
                else if((a4 != 0)||(a4 != 1)){
                    Toast.makeText(getApplicationContext(), "Please use valid bit for choices", Toast.LENGTH_LONG).show();
                }
                else if(mark < 0 || mark > 10 ){
                    Toast.makeText(getApplicationContext(), "Please use valid mark for mcq", Toast.LENGTH_LONG).show();
                }
                else {
                    PreparedStatement ps = mcq.AddMcq(syllabusId,mcqdes,mark);
                    myps = ps;
                    int mcq_id = mcq.getmcqId(); //excecute ps and return its mcq ID.
                    PreparedStatement ps1 = mcq.AddMcqAswers(mcq_id,ch1,a1);
                    PreparedStatement ps2 = mcq.AddMcqAswers(mcq_id,ch2,a2);
                    PreparedStatement ps3 = mcq.AddMcqAswers(mcq_id,ch3,a3);
                    PreparedStatement ps4 = mcq.AddMcqAswers(mcq_id,ch4,a4);

                    try {
                        if((ps1.executeUpdate()>0 && ps2.executeUpdate()>0)&&(ps3.executeUpdate()>0 && ps4.executeUpdate()>0)){
                            Toast.makeText(getApplicationContext(), "Sucessfully created a New mcq..", Toast.LENGTH_LONG).show();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }


                }





            }
        });






    }



    public static PreparedStatement getmyps(){
        return myps;
    }
}
