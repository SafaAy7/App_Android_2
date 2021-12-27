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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EvenmentActivity extends AppCompatActivity {

    EditText editTextNom,editTextDate,editTextContact,editTextAddress,editeTextDescription,editTextResponsable;
    ImageView imageView;
    Button save,retour,chooser;
    final static int REQUESTCODE=1;
    Uri path;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evenment);

        editTextNom = (EditText)findViewById(R.id.editTextNomEvenmentInsert);
        editTextDate = (EditText)findViewById(R.id.editTextDateEvenmentInsert);
        editTextContact = (EditText)findViewById(R.id.editTextContactEvenmentInsert);
        editTextAddress = (EditText)findViewById(R.id.editTextAddressEvenmentInsert);
        editeTextDescription = (EditText)findViewById(R.id.editTextDescriptionEvenmentInsert);
        editTextResponsable = (EditText)findViewById(R.id.editTextResponsableEvenmentInsert);
        save = (Button) findViewById(R.id.buttonSaveImageEvInsc);
        retour = (Button) findViewById(R.id.buttonRetour);
        chooser = (Button) findViewById(R.id.buttonChooserImageEvInsc);
        imageView = (ImageView)findViewById(R.id.imageView9);

        chooser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,"chooser Image"),REQUESTCODE);
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://20.20.0.206:82/pfaapp/evenments/insertEvenment.php", new Response.Listener<String>() {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.1.6:82/pfaapp/evenments/insertEvenment.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("nom",editTextNom.getText().toString());
                        params.put("date",editTextDate.getText().toString());
                        params.put("address",editTextAddress.getText().toString());
                        params.put("contact",editTextContact.getText().toString());
                        params.put("description",editeTextDescription.getText().toString());
                        params.put("responsable",editTextResponsable.getText().toString());
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
            }

        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUESTCODE && resultCode==RESULT_OK && data!=null){
            path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }



}
