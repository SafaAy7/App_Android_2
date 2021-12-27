package com.example.hp.pfa_app;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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
import java.util.Timer;
import java.util.TimerTask;


public class EvenmentFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    EvenmentAdapter evenmentAdapter;
    List<Evenment> evenments;
    TextView textView;
    LinearLayoutManager layoutManager;
    View v;
    ViewPagerEvenment viewPagerEvenment;
    ViewPager viewPager;
    int i=0;


    public EvenmentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EvenmentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EvenmentFragment newInstance(String param1, String param2) {
        EvenmentFragment fragment = new EvenmentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        onResume();
        v = inflater.inflate(R.layout.fragment_evenment, container, false);
        recyclerView = (RecyclerView)v.findViewById(R.id.rec4);
        textView = (TextView)v.findViewById(R.id.textViewEvFrag);

       // viewPager = (ViewPager)v.findViewById(R.id.viewpager2);

        evenments = new ArrayList<>();
        recyclerView.hasFixedSize();

//        StringRequest stringRequest = new StringRequest(Request.Method.GET,"http://20.20.0.206:82/pfaapp/evenments/all_evenments.php", new Response.Listener<String>() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,"http://192.168.1.6:82/pfaapp/evenments/all_evenments.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i=0;i<array.length();i++){
                        JSONObject object = array.getJSONObject(i);
                       // Toast.makeText(getContext(), object.getString("nom"), Toast.LENGTH_SHORT).show();
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

                       // textView.setText(textView.getText()+","+object.toString());
/*
                        Toast.makeText(getContext(),object.toString(),Toast.LENGTH_SHORT).show();
 */                       evenments.add(e);


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
        RequestQueue requestQueue = Volley.newRequestQueue(v.getContext());
        requestQueue.add(stringRequest);

     //   Toast.makeText(getContext(), "size = "+evenments.size(), Toast.LENGTH_SHORT).show();
        layoutManager = new LinearLayoutManager(v.getContext());
        recyclerView.setLayoutManager(layoutManager);

        evenmentAdapter = new EvenmentAdapter(v.getContext(),evenments,false);
        recyclerView.setAdapter(evenmentAdapter);

       // viewPagerEvenment = new ViewPagerEvenment(getContext(), (ArrayList<Evenment>) evenments);
     //   viewPager.setAdapter(viewPagerEvenment);

     /*   Timer timer = new Timer();
        timer.schedule(new EvenmentFragment.MyTimerTask(),2000,4000);*/
        return v;
    }





/*
class MyTimerTask extends TimerTask {

    @Override
    public void run() {
        EvenmentFragment.this.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d("data", String.valueOf(i));
                i++;
                //if(i>evenments.size())i=0;
            //    viewPager.setCurrentItem(0);
              //  Log.d("vpage==>",String.valueOf(viewPager.getCurrentItem()));
            }
        });
    }
}

*/



    public void fetchData(){

    }


}
