package com.example.hp.pfa_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ViewPager viewPager;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction1,fragmentTransaction2,fragmentTransaction3,fragmentTransaction4,fragmentTransaction5;
    FrameLayout frameLayout1,frameLayout2,frameLayout3,frameLayout4,frameLayout5,frameLayout6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        frameLayout1 = (FrameLayout)findViewById(R.id.frame3);
        frameLayout2 = (FrameLayout)findViewById(R.id.frame4);
        frameLayout3 = (FrameLayout)findViewById(R.id.frame5);
        frameLayout4 = (FrameLayout)findViewById(R.id.frame6);
        frameLayout5 = (FrameLayout)findViewById(R.id.frame7);

        fragmentManager = getSupportFragmentManager();

        fragmentTransaction3 = fragmentManager.beginTransaction();
        fragmentTransaction3.add(R.id.frame3,new AcueilFragment());
        fragmentTransaction3.commit();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction1 = fragmentManager.beginTransaction();
        fragmentTransaction1.add(R.id.frame4,new MembreFragment());
        fragmentTransaction1.commit();

        fragmentTransaction2 = fragmentManager.beginTransaction();
        fragmentTransaction2.add(R.id.frame5,new EvenmentFragment());
        fragmentTransaction2.commit();




        fragmentTransaction4 = fragmentManager.beginTransaction();
        fragmentTransaction4.add(R.id.frame6,new GalerieFragment());
        fragmentTransaction4.commit();

        fragmentTransaction5 = fragmentManager.beginTransaction();
        fragmentTransaction5.add(R.id.frame7,new ContactFragment());
        fragmentTransaction5.commit();

        frameLayout1.setVisibility(View.GONE);
        frameLayout2.setVisibility(View.VISIBLE);
        frameLayout3.setVisibility(View.GONE);
        frameLayout4.setVisibility(View.GONE);
        frameLayout5.setVisibility(View.GONE);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Go to  Page Login", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




        viewPager = (ViewPager)findViewById(R.id.viewpager);

        viewPager.setVisibility(View.GONE);
/*

        ViewPagerMain pagerAdapter = new ViewPagerMain(this);
        viewPager.setAdapter(pagerAdapter);

        Timer timer = new Timer();
        timer.schedule(new HomeActivity.MyTimerTask(),2000,4000);
  */  }


    class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            HomeActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(viewPager.getCurrentItem()==0){
                        viewPager.setCurrentItem(1);
                    }else if (viewPager.getCurrentItem()==1){
                        viewPager.setCurrentItem(2);
                    }else{
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
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
      //  getMenuInflater().inflate(R.menu.activity_home_drawer,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(item.getItemId()==R.id.home_main){
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }


            return true;


    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_acueil) {
            // Handle the camera action

            frameLayout1.setVisibility(View.VISIBLE);
            frameLayout2.setVisibility(View.GONE);
            frameLayout3.setVisibility(View.GONE);
            frameLayout4.setVisibility(View.GONE);
            frameLayout5.setVisibility(View.GONE);


        } else if (id == R.id.nav_membre) {

            frameLayout1.setVisibility(View.GONE);
            frameLayout2.setVisibility(View.VISIBLE);
            frameLayout3.setVisibility(View.GONE);
            frameLayout4.setVisibility(View.GONE);
            frameLayout5.setVisibility(View.GONE);



        } else if (id == R.id.nav_evenment) {
            frameLayout1.setVisibility(View.GONE);
            frameLayout2.setVisibility(View.GONE);
            frameLayout3.setVisibility(View.VISIBLE);
            frameLayout4.setVisibility(View.GONE);
            frameLayout5.setVisibility(View.GONE);





        } else if (id == R.id.nav_gallery) {

            frameLayout1.setVisibility(View.GONE);
            frameLayout2.setVisibility(View.GONE);
            frameLayout3.setVisibility(View.GONE);
            frameLayout4.setVisibility(View.VISIBLE);
            frameLayout5.setVisibility(View.GONE);

        } else if (id == R.id.nav_contact) {
            frameLayout1.setVisibility(View.GONE);
            frameLayout2.setVisibility(View.GONE);
            frameLayout3.setVisibility(View.GONE);
            frameLayout4.setVisibility(View.GONE);
            frameLayout5.setVisibility(View.VISIBLE);


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
