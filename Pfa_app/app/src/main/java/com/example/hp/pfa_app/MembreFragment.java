package com.example.hp.pfa_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class MembreFragment extends Fragment {

    final String URL = "http://192.168.1.6:82/pfaapp/membre/all_membre.php";
    RecyclerView recyclerView;
    List<Membre> membres;
    MembreAdapter membreAdapter;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public MembreFragment() {

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        onResume();
        View v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_membre, container, false);
        recyclerView = v.findViewById(R.id.rec5);
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(getContext(),2);
        membres = new ArrayList<>();
        sharedPreferences = this.getActivity().getSharedPreferences("Membre",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i=0;i<array.length();i++){
                        JSONObject object = array.getJSONObject(i);
                        membres.add(
                                new Membre(
                                        object.getInt("id"),
                                        object.getString("nom"),
                                        object.getString("prenom"),
                                        object.getInt("cin"),
                                        object.getString("poste"),
                                        object.getString("image")
                                )
                        );


                     //   Toast.makeText(getContext(),membres.get(i).getResponsablite(),Toast.LENGTH_LONG).show();
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
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

        membreAdapter = new MembreAdapter(getContext(),membres);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(membreAdapter);

        return v;
    }


}
