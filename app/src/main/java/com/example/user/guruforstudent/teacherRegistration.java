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
import com.example.user.guruforstudent.Models.teacher;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.sql.PreparedStatement;

public class teacherRegistration extends AppCompatActivity {




 EditText phone;
 EditText nic;
 EditText school;
 EditText eduqulify;
 EditText subjects;
 EditText another;
 FirebaseAuth auth;
 Button regbtn;

 @Override
 protected void onCreate(Bundle savedInstanceState) {
             getSupportActionBar().hide();
             super.onCreate(savedInstanceState);
             setContentView(R.layout.activity_teacher_registration);
             phone = (EditText)findViewById(R.id.phone);
             nic = (EditText)findViewById(R.id.nic);
             school = (EditText)findViewById(R.id.school);
             eduqulify = (EditText)findViewById(R.id.eduq);
             subjects = (EditText)findViewById(R.id.subject);
             another = (EditText)findViewById(R.id.another);
             regbtn = (Button)findViewById(R.id.regtoIns);
             auth = FirebaseAuth.getInstance();






 regbtn.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View view) {
 // Toast.makeText(getApplicationContext(), "button clicked " , Toast.LENGTH_LONG).show();
 if (phone.getText().length() == 0) {
     Toast.makeText(getApplicationContext(), "Phone number is Empty", Toast.LENGTH_LONG).show();
 }

 else if ((nic.getText().length() != 0)&&(nic.getText().length() != 10 || nic.getText().toString().charAt(9) != 'V' )){
 Toast.makeText(getApplicationContext(), "Not Valid ID number", Toast.LENGTH_LONG).show();
 }


 else if (school.getText().length() == 0) {
 Toast.makeText(getApplicationContext(), "Please mention Your Current Working Place", Toast.LENGTH_LONG).show();
 }
 else if (eduqulify.getText().length() == 0 ) {
 Toast.makeText(getApplicationContext(), "Please mention Your Degree or Diploma", Toast.LENGTH_LONG).show();
 }
 else if (subjects.getText().length() == 0) {
 Toast.makeText(getApplicationContext(), "Please mention Your teaching Subjects", Toast.LENGTH_LONG).show();
 }
 else if (phone.getText().length() != 10) {
 Toast.makeText(getApplicationContext(), "Not Valid phone number", Toast.LENGTH_LONG).show();
 }
 else if (phone.getText().charAt(0) != '0') {
 Toast.makeText(getApplicationContext(), "Not Valid phone number", Toast.LENGTH_LONG).show();

 }


 else {

 String tel = phone.getText().toString();
 String nicnum = nic.getText().toString();
 String sch = school.getText().toString();
 String edu = eduqulify.getText().toString();

 String subject = subjects.getText().toString();
 String anothe = another.getText().toString();
 PreparedStatement ps = teacher.teachReg(nicnum,tel,sch,edu,subject,anothe);
  String returnEmail = getIntent().getStringExtra("Email");
  String returnPassword = getIntent().getStringExtra("Password");



 try {
 if (ps.executeUpdate() > 0) {

  // Create user account with user name and password Authentication With Firebase
  auth.createUserWithEmailAndPassword(returnEmail,returnPassword).addOnCompleteListener(teacherRegistration.this, new OnCompleteListener<AuthResult>() {
   @Override
   public void onComplete(@NonNull Task<AuthResult> task) {
    if(!task.isSuccessful()){
     Toast.makeText(teacherRegistration.this,"Something wrong with Firebase",Toast.LENGTH_LONG).show();
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
     Toast.makeText(getApplicationContext(), "........................" + e.getMessage(), Toast.LENGTH_LONG).show();
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