package com.example.hp.pfa_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
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


public class DonateurListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DonateurAdapter donateurAdapter;
    List<Donateur> donateurs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donateur_list);

        recyclerView = (RecyclerView)findViewById(R.id.rec6);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(layoutManager);

        onResume();
        donateurs= new ArrayList<>();

        StringRequest stirngRequest = new StringRequest(Request.Method.POST,"http://192.168.1.6:82/pfaapp/donateur/all_donateur.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray array = new JSONArray(response);

                    for (int i=0;i<array.length();i++){
                        JSONObject object = array.getJSONObject(i);
                        Donateur d =  new Donateur(
                                object.getInt("id"),
                                object.getString("nom"),
                                object.getString("prenom"),
                                object.getString("email"),
                                object.getString("phone"),
                                object.getString("montantsoutient"),
                                object.getString("image")
                        );
                        donateurs.add(
                               d
                        );
                       // Toast.makeText(getApplicationContext(),object.getString("nom")+"---",Toast.LENGTH_LONG).show();
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
        requestQueue.add(stirngRequest);

        donateurAdapter = new DonateurAdapter(getApplicationContext(),donateurs);
        recyclerView.setAdapter(donateurAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.donateur_item,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        if(item.getItemId()==R.id.donateuradd){
            startActivity(new Intent(getApplicationContext(),DonateurAddActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
