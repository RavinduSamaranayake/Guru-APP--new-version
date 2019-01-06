package com.example.user.guruforstudent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class syllabusDescription extends AppCompatActivity {
    TextView nameview;
    TextView desc;
    TextView points;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus_description);

        String name = getIntent().getStringExtra("name");
        String descript = getIntent().getStringExtra("description");
        String lpoins = getIntent().getStringExtra("points");
        nameview = (TextView) findViewById(R.id.name);
        desc = (TextView) findViewById(R.id.description);
        points = (TextView) findViewById(R.id.learningpoint);
        nameview.setText(name);
        desc.setText(descript);
        points.setText(lpoins);
    }
}
