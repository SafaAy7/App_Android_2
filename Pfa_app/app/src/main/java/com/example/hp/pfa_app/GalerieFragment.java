package com.example.hp.pfa_app;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
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
import java.util.Arrays;
import java.util.List;


public class GalerieFragment extends Fragment {



   RecyclerView recyclerView;
    GalerieAdapter galerieAdapter;
    List<Galerie> galeries;
    String link="http://192.168.1.6:82/pfaapp/galerie/all_galerie.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_galerie, container, false);
        recyclerView = v.findViewById(R.id.recyclergalerie);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(layoutManager);

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
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);        galerieAdapter = new GalerieAdapter(getContext(),galeries);
        recyclerView.setAdapter(galerieAdapter);

  return v;


    }


}
