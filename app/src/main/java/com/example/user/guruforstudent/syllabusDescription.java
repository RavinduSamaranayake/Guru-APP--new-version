package com.example.user.guruforstudent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.guruforstudent.Models.User;

public class syllabusDescription extends AppCompatActivity {
    TextView nameview;
    TextView desc;
    TextView points;
    Button getReviews;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_syllabus_description);
        String name = getIntent().getStringExtra("name");
        String descript = getIntent().getStringExtra("description");
        String lpoins = getIntent().getStringExtra("points");
        final String courseId = getIntent().getStringExtra("courseId");
        user = new User();
        int ulevel = user.getCurIdCurLevel();
        nameview = (TextView) findViewById(R.id.name);
        desc = (TextView) findViewById(R.id.description);
        points = (TextView) findViewById(R.id.learningpoint);
        getReviews = (Button) findViewById(R.id.mcqShow);
        if(ulevel == 4){
            getReviews.setVisibility(View.VISIBLE);
        }
        else{
            getReviews.setVisibility(View.INVISIBLE);
        }
        nameview.setText(name);
        desc.setText(descript);
        points.setText(lpoins);
        getReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mcqIntent = new Intent(syllabusDescription.this,showMcq.class);
                mcqIntent.putExtra("courseId",courseId);
                startActivity(mcqIntent);
            }
        });
    }
}
