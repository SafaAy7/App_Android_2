package com.example.hp.pfa_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EvenmentListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView;
    List<Evenment> evenments;
    EvenmentAdapter evenmentAdapter;
    String link = "http://192.168.1.6:82/pfaapp/evenments/all_evenments.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evenment_list);

        evenments = new ArrayList<>();
        getData();

        recyclerView =(RecyclerView) findViewById(R.id.rec3);

        recyclerView.hasFixedSize();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        evenmentAdapter = new EvenmentAdapter(getApplicationContext(),evenments,true);
        recyclerView.setAdapter(evenmentAdapter);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        fab.setVisibility(View.GONE);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.evenment_menu, menu);
        MenuBuilder builder = (MenuBuilder)menu;

        if(builder instanceof Menu) {
            builder.setOptionalIconsVisible(true);
        }
            return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.gotoinsert) {
            startActivity(new Intent(getApplicationContext(),EvenmentActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        View v=(View) item;
        if (id == R.id.nav_camera) {
            // Handle the camera action
            Snackbar.make(v,"Camera view",Snackbar.LENGTH_SHORT).setAction("Data",null).show();
        } else if (id == R.id.nav_gallery) {
            Snackbar.make(v,"Camera view",Snackbar.LENGTH_SHORT).setAction("Data",null).show();

        } else if (id == R.id.nav_slideshow) {
            Snackbar.make(v,"Camera view",Snackbar.LENGTH_SHORT).setAction("Data",null).show();

        } else if (id == R.id.nav_manage) {
            Snackbar.make(v,"Camera view",Snackbar.LENGTH_SHORT).setAction("Data",null).show();

        } else if (id == R.id.nav_share) {
            Snackbar.make(v,"Camera view",Snackbar.LENGTH_SHORT).setAction("Data",null).show();

        } else if (id == R.id.nav_send) {
            Snackbar.make(v,"Camera view",Snackbar.LENGTH_SHORT).setAction("Data",null).show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void getData(){
      //  Toast.makeText(getApplicationContext(), "entering", Toast.LENGTH_SHORT).show();

        StringRequest  stringRequest = new StringRequest(Request.Method.GET,link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray array = new JSONArray(response);


                    for(int i=0;i<array.length();i++){
                        JSONObject object = array.getJSONObject(i);

                        Evenment e =  new Evenment(
                                object.getInt("id"),
                                object.getString("nom"),
                                object.getString("date"),
                                object.getString("address"),
                                String.valueOf(object.getInt("contact")),
                                object.getString("description"),
                                object.getString("responsable"),
                                object.getString("image")
                        );


                        evenments.add(e);

                     //   Toast.makeText(getApplicationContext(), "===>"+evenments.size(), Toast.LENGTH_LONG).show();
                       // Toast.makeText(getApplicationContext(), "===>"+e.getNom()+"---"+e.getDate(), Toast.LENGTH_LONG).show();

                  //      Toast.makeText(getApplicationContext(), ""+object.toString(), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }




}
