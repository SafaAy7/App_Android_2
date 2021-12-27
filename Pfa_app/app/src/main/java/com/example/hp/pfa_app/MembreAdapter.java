package com.example.hp.pfa_app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by hp on 15/05/2021.
 */

public class MembreAdapter extends RecyclerView.Adapter<MembreAdapter.MembreHolder> {
    Context mCtx;
    List<Membre> membres;


    public MembreAdapter(Context mCtx, List<Membre> membres) {
        this.mCtx = mCtx;
        this.membres = membres;
    }

    @Override
    public MembreHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mCtx.getApplicationContext()).inflate(R.layout.membre_item,parent,false);
        return new MembreHolder(v);
    }

    @Override
    public void onBindViewHolder(MembreHolder holder, int position) {
        Membre membre = membres.get(position);
        holder.fullname.setText(membre.getPrenom()+" "+membre.getNom());
        holder.responsabilite.setText(membre.getResponsablite());
    }

    @Override
    public int getItemCount() {
        return membres.size();
    }

    class MembreHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView fullname,responsabilite;

        public MembreHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView8);
            fullname = itemView.findViewById(R.id.textViewFullNameMembre);
            responsabilite = itemView.findViewById(R.id.textViewResponsableMembre);
        }
    }
}
