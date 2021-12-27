package com.example.hp.pfa_app;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
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



public class ListOrphActivity extends AppCompatActivity {

    String url = "http://192.168.1.6:82/pfaapp/orphelin/all_orphelin.php";
    RecyclerView rec2;
    orphelin_adapter orphelin_adapter;
    ArrayList<Orphelin> orphelins,fullList;
    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_orph);
        rec2 = (RecyclerView)findViewById(R.id.rec2);
        orphelins = new ArrayList<>();
        fullList = new ArrayList<>();
        fetchData();
        orphelin_adapter = new orphelin_adapter(this,orphelins,fullList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rec2.setAdapter(orphelin_adapter);
        rec2.setLayoutManager(layoutManager);



    orphelin_adapter.OnItemClickListener(new orphelin_adapter.OnItemClickListener() {
        @Override
        public void onItemClickListener(int position) {
            orphelins.get(position);
            Toast.makeText(getApplicationContext(), orphelins.get(position).getNom(), Toast.LENGTH_SHORT).show();
            Bundle b = new Bundle();
            Intent intent = new Intent(getApplicationContext(), ModificationOrphelin.class);
            b.putString("nom",orphelins.get(position).getNom());
            b.putString("prenom",orphelins.get(position).getPrenom());
            b.putString("age",String.valueOf(orphelins.get(position).getAge()));
            b.putString("mere_nom",orphelins.get(position).getNom_mere());
            b.putString("mere_cin",orphelins.get(position).getCin_mere());
            b.putString("niveauscolaire",orphelins.get(position).getNiveau_scolaire());
            b.putString("address",orphelins.get(position).getAdresse());
            b.putStringArrayList("orphelin",orphelins.get(position));
            b.putInt("id",orphelins.get(position).getId());
            intent.putExtras(b);
            startActivity(intent);

        }
    });

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.refreshswippe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                startActivity(new Intent(getApplicationContext(),ListOrphActivity.class));
               orphelins.add(new Orphelin(4,"eeee","hghgj",22,"tttt","54545",46465465,"bfvhj","nfdkjbk","nfbjkfbnk"));

                orphelin_adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });


    }




    public void fetchData(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i=0;i<array.length();i++){
                        JSONObject object = array.getJSONObject(i);
                        orphelins.add(new Orphelin(
                                object.getInt("id"),
                                object.getString("nom"),
                                object.getString("prenom"),
                                object.getInt("age"),
                                object.getString("nommere"),
                                object.getString("cinmere"),
                                object.getInt("telephone"),
                                object.getString("niveauscolaire"),
                                object.getString("address"),
                                object.getString("image")
                        ));
                        fullList.add(orphelins.get(i));
              //          Toast.makeText(getApplicationContext(),object.getString("nom")+" "+object.getString("prenom"),Toast.LENGTH_LONG).show();

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search,menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                orphelin_adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        if(item.getItemId()==R.id.action_add){
            startActivity(new Intent(getApplicationContext(),OrphActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
