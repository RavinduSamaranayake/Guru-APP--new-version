package com.example.user.guruforstudent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.guruforstudent.Models.Course;
import com.example.user.guruforstudent.Models.Institue;
import com.example.user.guruforstudent.Models.Student;
import com.example.user.guruforstudent.Models.User;
import com.example.user.guruforstudent.Models.insStudent;
import com.example.user.guruforstudent.Models.insTeacher;
import com.example.user.guruforstudent.Models.teacher;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ChooseInstitue extends AppCompatActivity {
    Spinner crsSpin;
    Spinner spin;
    EditText regid;
    Button nxt;
   // Button logout;
    TextView regtxt;
    int insId;
    int crsId;
    int teacherId;
    int studentId;
    Institue ins = new Institue();
    Course crs = new Course();
    User u = new User();
    teacher t = new teacher();
    Student st = new Student();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_reg);
        getSupportActionBar().hide();
        spin = (Spinner) findViewById(R.id.insNameSpin);
        crsSpin = (Spinner) findViewById(R.id.crsNameSpin);
        regid = (EditText) findViewById(R.id.reg);
        nxt = (Button) findViewById(R.id.toMainpg);
        // logout = (Button)findViewById(R.id.LogOut);
        regtxt = (TextView) findViewById(R.id.tvregnm);

        loadSpin();
        //loadCrsSpin("E Soft Metro Campus");
        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(u.getCurIdCurLevel()==4){   // verify to the user level is student
                    int stid = st.getCurStId();
                    //String stdid = Integer.toString(stid);
                    String regnum = regid.getText().toString();
                    insStudent ist = new insStudent();
                    PreparedStatement psist = ist.fillInsStTable(insId,stid,crsId,regnum,0);
                    try {
                        if(psist.executeUpdate()>0){
                            Toast.makeText
                                    (getApplicationContext(), "Student Registration is Sucessfully ", Toast.LENGTH_SHORT)
                                    .show();
                            waitreg();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                else if(u.getCurIdCurLevel()==3){ //user level is teacher
                    int tid = t.getCurTeachId(); //get currrent teacher id
                   // String teachId = Integer.toString(tid);
                    System.out.println("******************TTTTTTTTTTTT*************teacher id is : "+tid+" *******************************");
                    String regnum = regid.getText().toString();
                    insTeacher ite = new insTeacher();
                    PreparedStatement psist = ite.fillInsTeachTable(insId,tid,crsId,regnum,0); //fill teacher institute table
                    try {
                        if(psist.executeUpdate()>0){
                            Toast.makeText
                                    (getApplicationContext(), "Teacher Registration is Sucessfully ", Toast.LENGTH_SHORT)
                                    .show();
                            waitreg();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }


            }
        });
      /*  logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //FileRW rw = new FileRW();
                //rw.clearTheFile();
                tologinpg();

            }
        }); */
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                insId = ins.getInsId(selectedItemText); //to get institute Id

                // Notify the selected item  text
                loadCrsSpin(selectedItemText);
                Toast.makeText
                        (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        crsSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                crsId = crs.getCrsId(selectedItemText); //to get the crs Id

                // Notify the selected item  text

                Toast.makeText
                        (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void waitreg() {
        Intent intent = new Intent(this,WaitingReg.class);
        startActivity(intent);
    }


    private void openHomepg() {
        Intent intent = new Intent(this,Home.class);
        startActivity(intent);
    }

  /*  public void tologinpg() {
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);


    } */

    private void loadSpin() {

        List<String> insName = ins.getAllInstitutes();


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, insName);


        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        // attaching data adapter to spinner
        spin.setAdapter(dataAdapter);


    }

    public void loadCrsSpin(String insname) {
        Course crs = new Course();
        List<String> crsName = crs.getAllcrs(insname);


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, crsName);


        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        // attaching data adapter to spinner
        crsSpin.setAdapter(dataAdapter);


    }

}
