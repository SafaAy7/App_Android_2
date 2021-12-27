package com.example.hp.pfa_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class RegistrationEnqueteur extends AppCompatActivity {

    EditText EtNom,EtPrenom,EtCin,EtTel,EtEmail,EtAddress,EtPassword;
    Button btnSave,btnRetour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_enqueteur);

        EtNom = (EditText)findViewById(R.id.editTextNomEnqReg);
        EtPrenom = (EditText)findViewById(R.id.editTextPrenomReg);
        EtCin = (EditText)findViewById(R.id.editTextCinReg);
        EtEmail = (EditText)findViewById(R.id.editTextEmailReg);
        EtTel = (EditText)findViewById(R.id.editTextTelephoneReg);
        EtAddress = (EditText)findViewById(R.id.editTextAddressReg);
        EtPassword = (EditText)findViewById(R.id.editTextPasswordReg);

        btnSave = (Button)findViewById(R.id.buttonSAveReg);
        btnRetour = (Button)findViewById(R.id.buttonRetourReg);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"before",Toast.LENGTH_LONG).show();
                uploadData("http://20.20.1.77:82/pfaapp/enquaitaire/inscription.php");
                Toast.makeText(getApplicationContext(),"after",Toast.LENGTH_LONG).show();

            }
        });

    }

    public void uploadData(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray array = new JSONArray(response);
                    JSONObject object = array.getJSONObject(0);
                    Toast.makeText(getApplicationContext(),object.getString("response"),Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("nom",EtNom.getText().toString());
                params.put("prenom",EtPrenom.getText().toString());
                params.put("cin",EtCin.getText().toString());
                params.put("email",EtEmail.getText().toString());
                params.put("tel",EtTel.getText().toString());
                params.put("address",EtAddress.getText().toString());
                params.put("password",EtPassword.getText().toString());

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        MySingleTon.getmInstance(this).addToRequestQueue(stringRequest);
    }
}
