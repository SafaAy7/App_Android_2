package com.example.hp.pfa_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class AdminActivity extends AppCompatActivity {

    Button enq,orph,eve,galerie,buttonDonateur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        enq = (Button)findViewById(R.id.buttonEnquataire);
        orph = (Button)findViewById(R.id.buttonOrphelin);
        eve = (Button)findViewById(R.id.buttonEvenment);
        galerie = (Button)findViewById(R.id.buttonGalerie);
        buttonDonateur = (Button)findViewById(R.id.buttonDonateur);

        buttonDonateur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),DonateurListActivity.class));

            }
        });

        enq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ListEnqueteurActivity.class));
            }
        });

        orph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ListOrphActivity.class));
            }
        });

        eve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),EvenmentListActivity.class));
            }
        });

        galerie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ListGalerieActivity.class));
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.deconnecter,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        switch(item.getItemId()){
            case R.id.deconnection:
                startActivity(new Intent(this,LoginActivity.class));
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
