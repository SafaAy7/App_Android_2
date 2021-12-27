package com.example.hp.pfa_app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by hp on 17/06/2021.
 */

public class DonateurAdapter extends RecyclerView.Adapter<DonateurAdapter.DonateurHolder>  {
    Context mCtx;
    List<Donateur> donateurs;


    public DonateurAdapter(Context mCtx, List<Donateur> donateurs) {
        this.mCtx = mCtx;
        this.donateurs = donateurs;
    }

    @Override
    public DonateurHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mCtx).inflate(R.layout.donateur_item,parent,false);
        return new DonateurHolder(v);
    }

    @Override
    public void onBindViewHolder(DonateurHolder holder, int position) {
    Donateur donateur = donateurs.get(position);
        holder.textViewDonateurFullName.setText(donateur.getPrenom()+" "+donateur.getNom());
        holder.textViewDonateurEmail.setText(donateur.getEmail());
        holder.textViewDonateurPhone.setText(donateur.getPhone());
        holder.donateurMontantdesoutien.setText(donateur.getMontantsoutien());
        Picasso.with(mCtx).load("http://192.168.1.6:82/pfaapp/donateur/images/"+donateur.getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return donateurs.size();
    }

    class DonateurHolder extends RecyclerView.ViewHolder {
        TextView textViewDonateurFullName,textViewDonateurEmail,textViewDonateurPhone,donateurMontantdesoutien;
        ImageView imageView;
        public DonateurHolder(View itemView) {
            super(itemView);
            textViewDonateurFullName = itemView.findViewById(R.id.textView45);
            textViewDonateurEmail = itemView.findViewById(R.id.textViewDonateurEmail);
            textViewDonateurPhone = itemView.findViewById(R.id.Phone);
            donateurMontantdesoutien = itemView.findViewById(R.id.donateurMontantdesoutien);
            imageView = itemView.findViewById(R.id.imageView11);
        }
    }
}
