package com.example.user.guruforstudent;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.guruforstudent.Models.Student;
import com.example.user.guruforstudent.Models.User;
import com.example.user.guruforstudent.Models.insStudent;
import com.example.user.guruforstudent.Models.insTeacher;
import com.example.user.guruforstudent.Models.teacher;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.sql.PreparedStatement;

public class Login extends AppCompatActivity {

    //Connection con = null;
    PreparedStatement ps = null;
    Button tomain;
    Button userregpg;
    EditText username;
    EditText passwd;
    TextView tv;
    FirebaseAuth auth;
    User user;
    teacher t;
    Student st;
    insTeacher iteach;
    insStudent istud;
//    private static final String FILE_NAME = "test.txt";
   // User u = new User();
    public Login(){}

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        //con = MyConnection.getconnection();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tomain=(Button)findViewById(R.id.login);
        userregpg = (Button)findViewById(R.id.regBtn);
        username =(EditText)findViewById(R.id.txtUname);
        passwd =(EditText)findViewById(R.id.txtPasswd);

        auth = FirebaseAuth.getInstance(); //get firebase authentication
        user = new User();
        t = new teacher();
        st = new Student();
        iteach = new insTeacher();
        istud = new insStudent();


        if (auth.getCurrentUser() != null) { //check the user is registered or not logout the system
            if(user.getCurIdCurLevel()==3){//user level is teacher
                int tid = t.getCurTeachId(); //get current teacher id
               // String teachId = Integer.toString(tid);
                if(iteach.checkTeachStatus(tid)){ //check wether  institute registration status is 0 or 1
                    startActivity(new Intent(Login.this, Home.class)); //loading home page
                    finish();
                }
                else{ //if the registration status is 0
                    startActivity(new Intent(Login.this, WaitingReg.class)); //loading waitingreg page
                    finish();
                }
            }


            else if(user.getCurIdCurLevel()==4){//user level is student
                int stid = st.getCurStId(); //get current student id
                //String stdId = Integer.toString(stid);
                if(istud.checkStdStatus(stid)){ //check wether  institute registration status is 0 or 1
                    startActivity(new Intent(Login.this, Home.class)); //loading home page
                    finish();
                }
                else{ //if the registration status is 0
                    startActivity(new Intent(Login.this, WaitingReg.class)); //loading waitingreg page
                    finish();
                }
            }



        }

        tomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // save();
                String uname = username.getText().toString();
                String paswd = passwd.getText().toString();

                if (uname.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Username is Empty", Toast.LENGTH_LONG).show();
                } else if (paswd.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Password is Empty", Toast.LENGTH_LONG).show();
                }

                else {
                    auth.signInWithEmailAndPassword(uname, paswd).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(Login.this, "Username or password is invalid", Toast.LENGTH_LONG).show();
                            } else {
                                if (user.getCurIdCurLevel() == 3) {//user level is teacher
                                    int tid = t.getCurTeachId(); //get current teacher id
                                    // String teachId = Integer.toString(tid);
                                    if (iteach.checkTeachStatus(tid)) { //check wether  institute registration status is 0 or 1
                                        startActivity(new Intent(Login.this, Home.class)); //loading home page
                                        finish();
                                    } else { //if the registration status is 0
                                        startActivity(new Intent(Login.this, WaitingReg.class)); //loading waitingreg page
                                        finish();
                                    }
                                } else if (user.getCurIdCurLevel() == 4) {//user level is student
                                    int stid = st.getCurStId(); //get current student id
                                    // String stdId = Integer.toString(stid);
                                    if (istud.checkStdStatus(stid)) { //check wether  institute registration status is 0 or 1
                                        startActivity(new Intent(Login.this, Home.class)); //loading home page
                                        finish();
                                    } else { //if the registration status is 0
                                        startActivity(new Intent(Login.this, WaitingReg.class)); //loading waitingreg page
                                        finish();
                                    }
                                }
                            }

                        }
                    });
                }
            }
        });

        userregpg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // load();
                openregpg();
            }
        });

    }

    public void openregpg() {
        Intent intent = new Intent(this,userRegister.class);
        startActivity(intent);
    }
}
