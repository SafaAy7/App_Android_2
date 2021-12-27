package com.example.hp.pfa_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    String nom,prenom,cin,tel,image,address,email="",pass="";

    EditText Etemail,Etpassword;
    String email1="",pass1="";
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Etemail = (EditText)findViewById(R.id.editTextEmail);
        Etpassword = (EditText)findViewById(R.id.editTextPassword);
        login = (Button) findViewById(R.id.button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });


    }


    public void Login(String url) {
        email1 = Etemail.getText().toString();
        pass1 = Etpassword.getText().toString();

StringRequest stringRequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                    try {
                        JSONArray array = new JSONArray(response);
                        JSONObject object = array.getJSONObject(0);

                        nom = object.getString("nom");
                        prenom = object.getString("prenom");
                        cin = object.getString("cin");
                        tel = object.getString("tel");
                        email = object.getString("email");
                        address = object.getString("address");
                        image = object.getString("image");
                        pass = object.getString("password");




                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("email", Etemail.getText().toString());
                    params.put("password", Etpassword.getText().toString());
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

                  if (Etemail.getText().toString().equals("admin") && Etpassword.getText().toString().equals("admin")) {
                    startActivity(new Intent(LoginActivity.this, AdminActivity.class));
                    Toast.makeText(getApplicationContext(), "Connection Admin!!", Toast.LENGTH_LONG).show();

                } else
        if (email.equals(email1) && pass.equals(pass1)) {
            Toast.makeText(getApplicationContext(), "Connection success", Toast.LENGTH_LONG).show();

            Bundle b = new Bundle();
            b.putString("nom",nom);
            b.putString("prenom",prenom);
            b.putString("cin",cin);
            b.putString("tel",tel);
            b.putString("email",email);
            b.putString("address",address);
            Intent intent = new Intent(getApplicationContext(),ProfileEnqActivity.class);
            intent.putExtras(b);
            startActivity(intent);
        }else {
                    Toast.makeText(getApplicationContext(), "Connection failed!!", Toast.LENGTH_LONG).show();
                }


    }
}
