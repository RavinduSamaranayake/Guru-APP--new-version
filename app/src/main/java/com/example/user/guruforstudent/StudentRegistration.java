package com.example.user.guruforstudent;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.guruforstudent.Models.Student;
import com.example.user.guruforstudent.Models.User;
import com.example.user.guruforstudent.Models.teacher;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRegistration extends AppCompatActivity {
    EditText phone;
    EditText nic;
    EditText school;
    EditText age;
    EditText olindex;
    EditText alindex;
    Button regbtn;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);

        phone = (EditText)findViewById(R.id.phone);
        nic = (EditText)findViewById(R.id.nic);
        school = (EditText)findViewById(R.id.school);
        age = (EditText)findViewById(R.id.age);
        olindex = (EditText)findViewById(R.id.oLindex);
        alindex = (EditText)findViewById(R.id.ALindex);
        auth = FirebaseAuth.getInstance();
        regbtn = (Button)findViewById(R.id.regtoIns);
        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(getApplicationContext(), "button clicked " , Toast.LENGTH_LONG).show();
                if (phone.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Phone number is Empty", Toast.LENGTH_LONG).show();
                }

                else if ((nic.getText().length() != 0)&&(nic.getText().length() != 10 || nic.getText().toString().charAt(9) != 'V')){
                    Toast.makeText(getApplicationContext(), "Not Valid ID number", Toast.LENGTH_LONG).show();
                }

                else if (school.getText().length() == 0) {
                    Toast.makeText(getApplicationContext(), "School name is Empty", Toast.LENGTH_LONG).show();
                }
                else if (age.getText().toString().length() == 0 ) {
                    Toast.makeText(getApplicationContext(), "Your Age is Empty", Toast.LENGTH_LONG).show();
                }
                else if (Integer.parseInt(age.getText().toString()) > 50) {
                    Toast.makeText(getApplicationContext(), "Your Age is not not match as a student", Toast.LENGTH_LONG).show();
                }
                else if (phone.getText().length() != 10) {
                    Toast.makeText(getApplicationContext(), "Not Valid phone number", Toast.LENGTH_LONG).show();
                }
                else if (phone.getText().charAt(0) != '0') {
                    Toast.makeText(getApplicationContext(), "Not Valid phone number", Toast.LENGTH_LONG).show();

                }
                else if((olindex.getText().length()!=0)&&(!(olindex.getText().length()<=10 || olindex.getText().length()>=8))){
                    Toast.makeText(getApplicationContext(), "not valid OL index number", Toast.LENGTH_LONG).show();}
                else if((olindex.getText().length()!=0)&&(!(alindex.getText().length()<=10 || alindex.getText().length()>=7))){
                    Toast.makeText(getApplicationContext(), "not valid AL index number", Toast.LENGTH_LONG).show();}

                else {

                    String tel = phone.getText().toString();
                    String nicnum = nic.getText().toString();
                    String sch = school.getText().toString();
                    int ag = Integer.parseInt(age.getText().toString());
                    String ol = olindex.getText().toString();
                    String al = alindex.getText().toString();

                    PreparedStatement ps = Student.StReg(nicnum, tel, sch, ag, ol, al);
                    String returnEmail = getIntent().getStringExtra("Email");
                    String returnPassword = getIntent().getStringExtra("Password");

                    //PreparedStatement ps1 = userRegister.getps();
                    try {
                        if (ps.executeUpdate() > 0) {

                            //Save user in the Firebase DB
                            auth.createUserWithEmailAndPassword(returnEmail,returnPassword).addOnCompleteListener(StudentRegistration.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(!task.isSuccessful()){
                                        Toast.makeText(StudentRegistration.this,"Something wrong with Firebase",Toast.LENGTH_LONG).show();
                                    }else{
                                        Toast.makeText(getApplicationContext(), "Registration Sueccessfull ", Toast.LENGTH_LONG).show();
                                        chooseInspg();
                                        finish();
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(getApplicationContext(), "Registration fail", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "..........std Err.............." + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public void chooseInspg() {
        Intent intent = new Intent(this,ChooseInstitue.class);
        startActivity(intent);
    }
}



