package com.example.hp.pfa_app;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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



public class ListGalerieActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    GalerieAdapter galerieAdapter;
    List<Galerie> galeries;
    String link="http://192.168.1.6:82/pfaapp/galerie/all_galerie.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_galerie);

        recyclerView = (RecyclerView) findViewById(R.id.recyclergalerie_list);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);


        galeries = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Galerie galerie;
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i=0;i<array.length();i++){
                        JSONObject object = array.getJSONObject(i);
                        galerie = new Galerie(object.getInt("id"),object.getString("titre"),object.getString("contenu"),object.getInt("minage"),object.getInt("maxage"),object.getString("image"));
                        galeries.add(galerie);

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
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
        galerieAdapter = new GalerieAdapter(getApplicationContext(),galeries);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(galerieAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.galerie_menu,menu);
        MenuBuilder builder = (MenuBuilder)menu;

        builder.setOptionalIconsVisible(true);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        if(item.getItemId()==R.id.addgalerie){
            startActivity(new Intent(getApplicationContext(),GalerieAddActivity.class));

        }
        if(item.getItemId()==R.id.back){
            startActivity(new Intent(getApplicationContext(),AdminActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
