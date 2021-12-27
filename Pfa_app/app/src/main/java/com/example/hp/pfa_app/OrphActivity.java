package com.example.hp.pfa_app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class OrphActivity extends AppCompatActivity {

    EditText EtNom,EtPrenom,EtAge,EtnomMere,EtCinMere,EtNiveauScolaire,EtAddress,EtTelephone;
    String Nom,Prenom,Age,nomMere,CinMere,NiveauScolaire,Address,Telephone;
    ImageView imageView;

    String url="http://20.20.1.77:82/pfaapp/orphelin/registration1.php";
    Bitmap bitmap;
    final static int CODE_GAlERY_REQUEST=1;

    Button btnChosser,btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orph);

        EtNom = (EditText)findViewById(R.id.editTextNom);
        EtPrenom = (EditText)findViewById(R.id.editTextPrenom);
        EtAge = (EditText)findViewById(R.id.editTextAge);
        EtnomMere = (EditText)findViewById(R.id.editTextNomMere);
        EtCinMere = (EditText)findViewById(R.id.editTextCinMere);
        EtNiveauScolaire = (EditText)findViewById(R.id.editTextNivScolaire);
        EtTelephone = (EditText)findViewById(R.id.editTextTel);
        EtAddress = (EditText)findViewById(R.id.editTextAddress);
        btnChosser = (Button) findViewById(R.id.buttonChooserImage);
        btnSave = (Button) findViewById(R.id.buttonSave);
        imageView = (ImageView)findViewById(R.id.imageView);






        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                      //  Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();
                    }
                },new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                    //    Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_LONG).show();

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("nom",EtNom.getText().toString());
                        params.put("prenom",EtPrenom.getText().toString());
                        params.put("age",EtAge.getText().toString());
                        params.put("nommere",EtnomMere.getText().toString());
                        params.put("cinmere",EtCinMere.getText().toString());
                        params.put("niveauscolaire",EtNiveauScolaire.getText().toString());
                        params.put("telephone",EtTelephone.getText().toString());
                        params.put("address",EtAddress.getText().toString());

                        return params;
                    }
                };
                RequestQueue request = Volley.newRequestQueue(OrphActivity.this);
                request.add(stringRequest);
            }
        });


        btnChosser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("images/*");
                startActivityForResult(Intent.createChooser(intent,"Image selected!"),CODE_GAlERY_REQUEST);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CODE_GAlERY_REQUEST && resultCode==RESULT_OK&&data!=null){
            Uri filepath = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(filepath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }



}
