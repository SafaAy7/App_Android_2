package com.example.hp.pfa_app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ModificationOrphelin extends AppCompatActivity {

    EditText EtNom,EtPrenom,EtAge,EtnomMere,EtCinMere,EtNiveauScolaire,EtAddress,EtTelephone;
    ImageView imageView,imageButtonSupprimer;
    TextView txtNom,txtPrenom,txtAge,txtnomMere,txtCinMere,txtNiveauScolaire,txtAddress,txtTelephone;
    int id=0;
    Button btnChosser,btnSave;
    final  int REQ_CODE=1;
    Bitmap bitmap;

//    String url="http://20.20.0.206:82/pfaapp/orphelin/editerorph.php?id=";
    String url="http://192.168.1.6:82/pfaapp/orphelin/editerorph.php?id=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier__orphilan);

        EtNom = (EditText)findViewById(R.id.editTextNomMod);
        EtPrenom = (EditText)findViewById(R.id.editTextPrenomMod);
        EtAge = (EditText)findViewById(R.id.editTextAgeMod);
        EtnomMere = (EditText)findViewById(R.id.editTextNomMereMod);
        EtCinMere = (EditText)findViewById(R.id.editTextCinMereMod);
        EtNiveauScolaire = (EditText)findViewById(R.id.editTextNivScolaireMod);
        EtTelephone = (EditText)findViewById(R.id.editTextTelMod);
        EtAddress = (EditText)findViewById(R.id.editTextAddressMod);
        btnChosser = (Button) findViewById(R.id.buttonChooserImageMod);
        btnSave = (Button) findViewById(R.id.buttonSaveMod);
        imageView = (ImageView)findViewById(R.id.imageViewMod);
        imageButtonSupprimer = (ImageView)findViewById(R.id.imageButtonSupprimer);


        imageButtonSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Toast.makeText(getApplicationContext(), ""+id, Toast.LENGTH_SHORT).show();
                url="http://192.168.1.6:82/pfaapp/orphelin/supprimerorph.php?id="+id;
                StringRequest stringRequest = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //     Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }){
                    /*
                                @Override
                                public Map<String, String> getHeaders() throws AuthFailureError {
                                    Map<String,String> headers = new HashMap<>();
                                    headers.put("content-type","text/json");
                                    return headers;
                                }
                    */
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<>();
                         params.put("id",String.valueOf(id));
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
                startActivity(new Intent(getApplicationContext(),ListOrphActivity.class));
            }
        });

        txtNom = (TextView)findViewById(R.id.textViewOrphNom);
        txtPrenom = (TextView)findViewById(R.id.textViewOrphPrenom);
        txtAge = (TextView)findViewById(R.id.textViewOrphAge);
        txtnomMere = (TextView)findViewById(R.id.textViewOrphNomMere);
        txtCinMere = (TextView)findViewById(R.id.textViewOrphCinMere);
        txtTelephone = (TextView)findViewById(R.id.textViewOrphTel);
        txtNiveauScolaire = (TextView)findViewById(R.id.textViewOrphNiveauScolaire);
        txtAddress = (TextView)findViewById(R.id.textViewOrphAddress);

        init();

        btnChosser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,"Image Selected"),REQ_CODE);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData();


            }
        });

    }

    public void sendData(){

      //  Toast.makeText(getApplicationContext(), ""+id, Toast.LENGTH_SHORT).show();
        url="http://192.168.1.6:82/pfaapp/orphelin/editerorph.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
           //     Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            //    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }){
/*
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> headers = new HashMap<>();
                headers.put("content-type","text/json");
                return headers;
            }
*/
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("nom",EtNom.getText().toString());
                params.put("prenom",EtPrenom.getText().toString());
                params.put("age",EtAge.getText().toString());
                params.put("cinmere",EtCinMere.getText().toString());
                params.put("nommere",EtnomMere.getText().toString());
                params.put("telephone",EtTelephone.getText().toString());
                params.put("niveauscolaire",EtNiveauScolaire.getText().toString());
                params.put("address",EtAddress.getText().toString());
                params.put("id",String.valueOf(id));
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        startActivity(new Intent(this,ListOrphActivity.class));

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQ_CODE&&resultCode==RESULT_OK&&data!=null){
            Uri path = data.getData();
            try {
              //  bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                InputStream inputStream = getContentResolver().openInputStream(path);
                bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void init(){
        Bundle bundle = getIntent().getExtras();
        EtNom.setText((String)bundle.get("nom"));
        EtPrenom.setText((String)bundle.get("prenom"));
        EtAge.setText((String)bundle.get("age"));
        EtnomMere.setText((String)bundle.get("mere_nom"));
//        EtAge.setText(Integer.parseInt(String.valueOf(bundle.get("mere_cin"))), TextView.BufferType.EDITABLE);
        EtCinMere.setText(String.valueOf(bundle.get("mere_cin")));
        EtTelephone.setText(String.valueOf(bundle.get("tel")));
        EtNiveauScolaire.setText((String)bundle.get("niveauscolaire"));
        EtAddress.setText((String)bundle.get("address"));
        Picasso.with(getApplicationContext()).
        load("http://192.168.1.6:82/pfaapp/orphelin/images/orphelin/"
        +(String)bundle.getString("image")).into(imageView);
        txtPrenom.setText((String)bundle.get("prenom"));
        txtAge.setText((String)bundle.get("age"));
        txtnomMere.setText((String)bundle.get("mere_nom"));
        txtCinMere.setText(String.valueOf(bundle.get("mere_cin")));
        txtTelephone.setText(String.valueOf(bundle.get("tel")));
        txtNiveauScolaire.setText((String)bundle.get("niveauscolaire"));
        txtAddress.setText((String)bundle.get("address"));
        id = bundle.getInt("id");
        onResume();
    }



}
