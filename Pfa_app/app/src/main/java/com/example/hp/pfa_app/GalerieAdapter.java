package com.example.hp.pfa_app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by hp on 17/06/2021.
 */

public class GalerieAdapter extends RecyclerView.Adapter<GalerieAdapter.GalerieHolder>{
    Context mCtx;
    List<Galerie> galeries;
    String path="";


    public GalerieAdapter(Context mCtx, List<Galerie> galeries) {
        this.mCtx = mCtx;
        this.galeries = galeries;
    }

    @Override
    public GalerieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mCtx).inflate(R.layout.galeri_item,parent,false);
        return new GalerieHolder(v);
    }

    @Override
    public void onBindViewHolder( GalerieHolder holder, int position) {
        Galerie galerie = galeries.get(position);
        holder.txt_titre.setText(galerie.getTitre());
        holder.txt_content.setText(galerie.getContenu());
        holder.txt_age.setText("les enfant age entre "+galerie.getMinage()+" et "+galerie.getMaxage());
        Picasso.with(mCtx).load("http://20.20.1.77:82/pfaapp/galerie/images/"+galerie.getImage()).into(holder.imageView);




    }

    @Override
    public int getItemCount() {
        return galeries.size();
    }

    class GalerieHolder extends RecyclerView.ViewHolder{
        TextView txt_titre,txt_content,txt_age;
        ImageView imageView;
        public GalerieHolder(View itemView) {
            super(itemView);
            txt_titre = itemView.findViewById(R.id.textViewtitre);
            txt_content = itemView.findViewById(R.id.textViewContent);
            txt_age = itemView.findViewById(R.id.textViewAge);
            imageView = itemView.findViewById(R.id.imageView14);
        }
    }

}
