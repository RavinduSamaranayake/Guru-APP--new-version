package com.example.user.guruforstudent;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;


import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.user.guruforstudent.Models.User;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    NavigationView navigationView;
    View navHeaderView;
    Toolbar toolbar = null;
    FirebaseAuth auth;
    CardView addIns;
    CardView viewIns;
    CardView viewModule;
    CardView rateIns;
    CardView searchCrs;
    User user;

    private ProgressDialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        auth=FirebaseAuth.getInstance();
        user = new User();
        int cur_ulevel = user.getCurIdCurLevel();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        //navHeaderView = navigationView.inflateHeaderView(R.layout.nav_header_home);


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);
        TextView tvUname = (TextView)hView.findViewById(R.id.txtUname);
        TextView tvEmail = (TextView)hView.findViewById(R.id.txtEmail);
        List<String> userinfo = user.getUserInfo();
        String fullname = userinfo.get(0) + " " + userinfo.get(1);
        String uname = userinfo.get(2);
        tvUname.setText(fullname);
        tvEmail.setText(uname);

        navigationView.setNavigationItemSelectedListener(this);
        addIns = (CardView)findViewById(R.id.addInstitue);
        viewIns = (CardView)findViewById(R.id.viewInstitue);
        viewModule = (CardView)findViewById(R.id.viewModules);
        rateIns = (CardView)findViewById(R.id.rateInstitue);
        searchCrs = (CardView)findViewById(R.id.searchCrs);

        dialog = new ProgressDialog(Home.this);
        dialog.setMessage("Loading...");



        if(cur_ulevel == 4){
            rateIns.setVisibility(View.VISIBLE);
        }
        else{
            rateIns.setVisibility(View.INVISIBLE);
        }

        addIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAddInspg();
            }
        });
        rateIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadRateInspg();
            }
        });
        viewIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadViewInspg();
            }
        });
        viewModule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadViewModulepg();
            }
        });
        searchCrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadSearchCrsPg();
            }
        });

        
    }

    private void loadSearchCrsPg() {
        Intent intent = new Intent(this,searchCoursesWebview.class);
        startActivity(intent);
    }

    private void loadViewModulepg() {
        Intent intent = new Intent(this,InstituteList.class);
        startActivity(intent);
    }

    private void loadRateInspg() {
        Intent intent = new Intent(this,rateInstitute.class);
        startActivity(intent);
    }

    private void loadViewInspg() {
        Intent intent = new Intent(this,classes.class);
        startActivity(intent);
    }

    private void loadAddInspg() {
        dialog.show();
        Intent intent = new Intent(this,ChooseInstitue.class);
        startActivity(intent);
        dialog.dismiss();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")

    @Override




    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Home) {
            Intent h = new Intent(Home.this, Home.class);
            startActivity(h);
        } else if (id == R.id.setting) {
            Intent s = new Intent(Home.this, Settings.class);
            startActivity(s);

        } else if (id == R.id.help) {
            Intent l = new Intent(Home.this, Help.class);
            startActivity(l);

        }else if (id == R.id.logout){
            auth.signOut();
            startActivity(new Intent(Home.this,Login.class));
        }else if (id == R.id.aboutapp) {
            Intent a = new Intent(Home.this,about.class);
            startActivity(a);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
