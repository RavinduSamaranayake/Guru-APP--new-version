package com.example.user.guruforstudent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.guruforstudent.Models.Institue;
import com.example.user.guruforstudent.Models.Student;
import com.example.user.guruforstudent.Models.User;
import com.example.user.guruforstudent.Models.teacher;

import java.util.List;

public class rateInstitute extends AppCompatActivity {
    ListView list_1;
    Student std;
    teacher tch;
    User user;
    Institue institue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_institute);
        //getActionBar().hide();
        std = new Student();
        tch = new teacher();
        user =new User();
        institue = new Institue();
        int id;
        int ulevel = user.getCurIdCurLevel(); //check whether the current user level is student or teacher
        if(ulevel == 4) { //the current user is student
            id = std.getCurStId();
            final List<String> insName = std.getAllStRegIns(id); //this make final because use for inner class
            list_1 = (ListView)findViewById(R.id.rateInslistview);
            CustomClzList customClzList = new CustomClzList(this,insName);
            list_1.setAdapter(customClzList);
            list_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(rateInstitute.this,"Rate "+insName.get(position) ,Toast.LENGTH_SHORT).show();
                    String instname = insName.get(position); //get the institute name using clicked item index for insName list
                    int insId = institue.getInsId(instname); //to convert to the institute id
                    int stid = std.getCurStId(); //re get the current student id
                    openRateg(stid,insId); // to open the rate page


                }
            });
        }

    }

    private void openRateg(int stid, int insId) {
        Intent intent = new Intent(this, viewComments.class);
        String stdid = Integer.toString(stid);
        String instid = Integer.toString(insId);
        intent.putExtra("studentId",stdid); //to pass to the next page
        intent.putExtra("instituteId",instid);
        startActivity(intent);
    }
}

