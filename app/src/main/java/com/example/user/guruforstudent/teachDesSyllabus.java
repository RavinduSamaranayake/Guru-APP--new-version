package com.example.user.guruforstudent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.guruforstudent.Models.Syllabus;
import com.example.user.guruforstudent.Models.User;

public class teachDesSyllabus extends AppCompatActivity {

    TextView nameview;
    TextView desc;
    TextView points;
    Button addMcqs;
    User user;
    Syllabus syllabus;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_teach_des_syllabus);
        syllabus = new Syllabus();
        String name = getIntent().getStringExtra("name");
        id = syllabus.getsyllabusId(name);
        String descript = getIntent().getStringExtra("description");
        String lpoins = getIntent().getStringExtra("points");
        final String courseId = getIntent().getStringExtra("courseId");
        user = new User();
        int ulevel = user.getCurIdCurLevel();

        nameview = (TextView) findViewById(R.id.name);
        desc = (TextView) findViewById(R.id.description);
        points = (TextView) findViewById(R.id.learningpoint);
        addMcqs = (Button) findViewById(R.id.btnaddMcq);

        nameview.setText(name);
        desc.setText(descript);
        points.setText(lpoins);

        addMcqs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mcqIntent = new Intent(  teachDesSyllabus.this,addMcq.class);
                String syllabusId = Integer.toString(id);
                mcqIntent.putExtra("syllabusId",syllabusId);
                startActivity(mcqIntent);
            }
        });
    }
}
