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
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class GalerieAddActivity extends AppCompatActivity {

    EditText extTitre,extContenu,extMingAge,extMaxAge;
    Button btnSave,btnSelect;
    ImageView img;
    final  int REQUEST_CODE = 1;
    Uri path;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galerie_add);
        extTitre = (EditText)findViewById(R.id.editTextGalerieTitre);
        extContenu = (EditText)findViewById(R.id.editTextGalerieContenu);
        extMingAge = (EditText)findViewById(R.id.editTextGalerieMinAge);
        extMaxAge = (EditText)findViewById(R.id.editTextGalerieMaxAge);
        btnSave = (Button)findViewById(R.id.buttonEnregistrerGalerie);
        btnSelect = (Button)findViewById(R.id.buttonSelectImage);
        img = (ImageView) findViewById(R.id.imageView10);



        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,"choose image"),REQUEST_CODE);

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addGalerie();
            }
        });


    }

    void addGalerie(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,"http://192.168.1.6:82/pfaapp/galerie/add_galerie.php", new Response.Listener<String>() {
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
                Map<String,String> params = new HashMap<>();
                params.put("titre",extTitre.getText().toString());
                params.put("contenu",extContenu.getText().toString());
                params.put("minage",extMingAge.getText().toString());
                params.put("maxage",extMaxAge.getText().toString());
                params.put("image",imageToString(bitmap));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }



    String imageToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        String str = Base64.encodeToString(bytes,Base64.DEFAULT);
        return str;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE&&resultCode == RESULT_OK&&data!=null&&data.getData()!=null){
            path = data.getData();
            try {
                //bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                InputStream inputStream = getContentResolver().openInputStream(path);
                bitmap = BitmapFactory.decodeStream(inputStream);
                img.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
