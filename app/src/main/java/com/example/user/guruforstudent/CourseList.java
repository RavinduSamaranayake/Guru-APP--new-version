package com.example.user.guruforstudent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.guruforstudent.Models.Course;

import java.util.List;

public class CourseList extends AppCompatActivity {

    ListView list_1;
    InstituteList institutelist;
    Course crs;
    static int Crsid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institutes);
        list_1 = (ListView)findViewById(R.id.institutelist);
        crs = new Course();
        final List<String> crsNamelist = institutelist.getCrsnamelist(); //get the crsNamelist List from classes class...


        CustomMainList customMainList = new CustomMainList(this,crsNamelist);
        list_1.setAdapter(customMainList); //set the list items for list view
        list_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
              // Toast.makeText(CourseList.this,"List Item was clicked at "+position,Toast.LENGTH_SHORT).show();
               String crsname = crsNamelist.get(position);
               int crsid = crs.getCrsId(crsname);
               Crsid = crsid;
               openSyllabuspg();



            }
        });


    }

    private void openSyllabuspg() {
        Intent intent = new Intent(this,syllabusList.class);
        startActivity(intent);
    }

    public static int getCrsId(){
        return Crsid;
    }
}
