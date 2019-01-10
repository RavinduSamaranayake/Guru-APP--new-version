package com.example.user.guruforstudent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class Institutes extends AppCompatActivity {

   ListView list_1;
   classes institutelist; //create the object institutelist using classes class


    //String[] Institutes = {"Institutes 01","Institutes 02","Institutes 03"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institutes);
        list_1 = (ListView)findViewById(R.id.institutelist);
        List<String> crsNamelist = institutelist.getCrsnamelist(); //get the crsNamelist List from classes class...

        CustomMainList customMainList = new CustomMainList(this,crsNamelist);
        list_1.setAdapter(customMainList); //set the list items for list view



    }



}//in this class we set to the list items for list view usin CrsNameList
