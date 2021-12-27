package com.example.hp.pfa_app;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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


public class ListEnqueteurActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Enqueteur_adapter enquataire_adapter;
    List<Enqueteur> enquataires;
    String url="http://192.168.1.6:82/pfaapp/enquaitaire/enqueteur_lists.php";
   // String url="https://jsonplaceholder.typicode.com/posts";
    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_enq);
        recyclerView = (RecyclerView)findViewById(R.id.rc1);

        enquataires = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    //     Toast.makeText(ListEnqActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                    //    Toast.makeText(getApplicationContext(), ""+array.length(), Toast.LENGTH_SHORT).show();
                    for(int i=0;i<array.length();i++){
                        JSONObject object = array.getJSONObject(i);
                        String nom = object.getString("nom");
                        String prenom = object.getString("prenom");
                        int cin = object.getInt("cin");
                        String email = object.getString("email");
                        int tel = object.getInt("tel");
                        String address = object.getString("address");
                        String image = object.getString("image");
                        Enqueteur e = new Enqueteur(object.getInt("id"),
                                nom,prenom,String.valueOf(cin),email,object.getString("password"),String.valueOf(tel),address,image

                        );
                        enquataires.add(e );

                    /*    String nom = object.getString("title");
                        String prenom = object.getString("body");*/
                        //           Toast.makeText(getApplicationContext(),nom+" "+prenom,Toast.LENGTH_LONG).show();
                    }
//                    enquataire_adapter = new Enquataire_adapter(getApplicationContext(),enquataires);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue request = Volley.newRequestQueue(this);
        request.add(stringRequest);

        recyclerView.setHasFixedSize(true);

         RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        enquataire_adapter = new Enqueteur_adapter(getApplicationContext(),enquataires);



        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(enquataire_adapter);


        enquataire_adapter.OnItemClickListener(new Enqueteur_adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int posistion) {
              //  Toast.makeText(getApplicationContext(),String.valueOf(posistion),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), Modification_EnqueteurActivity.class);
                Bundle b = new Bundle();
                b.putString("id",String.valueOf(enquataires.get(posistion).getId()));
                b.putString("nom",String.valueOf(enquataires.get(posistion).getNom()));
                b.putString("prenom",String.valueOf(enquataires.get(posistion).getPrenom()));
                b.putString("cin",String.valueOf(enquataires.get(posistion).getCin()));
                b.putString("email",String.valueOf(enquataires.get(posistion).getEmail()));
                b.putString("tel",String.valueOf(enquataires.get(posistion).getTel()));
                b.putString("address",String.valueOf(enquataires.get(posistion).getAdresse()));
                b.putString("password",String.valueOf(enquataires.get(posistion).getPassword()));
                intent.putExtras(b);
                startActivity(intent);
            }
        });


        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.refreshswippe2);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
              //  startActivity(new Intent(getApplicationContext(),ListEnqActivity.class));

                enquataire_adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }



    public void loadData(){

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_enqueteur,menu);
        if(menu instanceof MenuBuilder){
            MenuBuilder menuBuilder = (MenuBuilder)menu;
            menuBuilder.setOptionalIconsVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        if (item.getItemId()==R.id.addeq) {

            //    Toast.makeText(getApplicationContext(), "Ouverture de l' Enquetaire....", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), EnqueteurActivity.class));
                }
            else if(item.getItemId()== R.id.backeq){
              //  Toast.makeText(getApplicationContext(), "Ouverture de l' Enquetaire....", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), AdminActivity.class));
                }
            else if(item.getItemId()== R.id.deconnectereq){
                //Toast.makeText(getApplicationContext(), "Ouverture de l' Enquetaire....", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }

            return false;


    }
}
