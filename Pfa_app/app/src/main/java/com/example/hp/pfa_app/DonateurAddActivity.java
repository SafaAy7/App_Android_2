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

public class DonateurAddActivity extends AppCompatActivity {

    EditText editTextDonateurNom,editTextDonateurPrenom,editTextDonateurEmail,editTextDonateurPhone,editTextDonateurMontantSoutient;
    Button buttonDonateurEnregistrer,buttonSelectImage;
    ImageView imageView;
    final int REQUEST_CODE = 1;
    Uri path;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donateur_add);
        imageView = (ImageView)findViewById(R.id.imageView12);
        editTextDonateurNom = (EditText)findViewById(R.id.editTextDonateurNom);
        editTextDonateurPrenom = (EditText)findViewById(R.id.editTextDonateurPrenom);
        editTextDonateurEmail = (EditText)findViewById(R.id.editTextDonateurEmail);
        editTextDonateurPhone = (EditText)findViewById(R.id.editTextDonateurPhone);
        editTextDonateurMontantSoutient = (EditText)findViewById(R.id.editTextDonateurMontantSoutient);
        buttonDonateurEnregistrer = (Button)findViewById(R.id.buttonDonateurEnregistrer);
        buttonSelectImage = (Button)findViewById(R.id.buttonSelectImage);

        buttonSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Donateur page"),REQUEST_CODE);
            }
        });



        buttonDonateurEnregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST,"http://192.168.1.6:82/pfaapp/donateur/add_donateur.php", new Response.Listener<String>() {
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
                        params.put("nom",editTextDonateurNom.getText().toString());
                        params.put("prenom",editTextDonateurPrenom.getText().toString());
                        params.put("email",editTextDonateurEmail.getText().toString());
                        params.put("phone",editTextDonateurPhone.getText().toString());
                        params.put("montantsoutient",editTextDonateurMontantSoutient.getText().toString());

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
        if(requestCode == REQUEST_CODE&&resultCode==RESULT_OK&&data!=null&&data.getData()!=null){
            path = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(path);
                bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }



}
