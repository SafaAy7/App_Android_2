package com.example.hp.pfa_app;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;



public class ProfileEnqActivity extends AppCompatActivity {
    String nom,prenom,cin,email,tel,pass,image,address;
    ImageView imageView;
    TextView txtVNom,txtVPrenom;
    Button enq1,even1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_enq);
        enq1 = (Button)findViewById(R.id.enq);
        even1 = (Button)findViewById(R.id.even);

        imageView = (ImageView)findViewById(R.id.imageView5);
        txtVNom = (TextView)findViewById(R.id.textViewNomProfile);
        txtVPrenom = (TextView)findViewById(R.id.textViewPrenomProfile);
        enq1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ListOrphActivity.class));
            }
        });
        even1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),EvenmentListActivity.class));
            }
        });
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        nom = b.getString("nom");
        prenom = b.getString("prenom");
        cin = b.getString("age");
        email = b.getString("email");
        tel = b.getString("tel");
        pass = b.getString("pass");
        image = b.getString("image");
        address = b.getString("address");

        txtVNom.setText(nom);
        txtVPrenom.setText(prenom);
        Picasso.with(getApplicationContext()).load("http://20.20.1.77:82/pfaapp/enquaitaire/images/enq/"+image).into(imageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.enqueteur_profile,menu);
        if(menu instanceof MenuBuilder){
            MenuBuilder menuBuilder = (MenuBuilder) menu;
            menuBuilder.setOptionalIconsVisible(true);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.gereProfile){
            Bundle b = new Bundle();
            b.putString("nom",nom);
            b.putString("prenom",prenom);
            b.putString("cin",cin);
            b.putString("tel",tel);
            b.putString("email",email);
            b.putString("address",address);
            b.putString("image",image);
            Intent intent = new Intent(getApplicationContext(),ListOrphActivity.class);
            intent.putExtras(b);
            startActivity(intent);
        }else if(item.getItemId()==R.id.deconnecterProfile){
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }

        return false;
    }
}
