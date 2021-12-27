package com.example.hp.pfa_app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by hp on 16/04/2021.
 */

public class orphelin_adapter  extends RecyclerView.Adapter<orphelin_adapter.OrphilanViewHolder> implements Filterable{

    Context mCtx;
    private List<Orphelin> orphelins;
    private List<Orphelin> fullList;
    OnItemClickListener mListener;



    public interface OnItemClickListener{
        void onItemClickListener(int position);
    }

    public  void OnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }
    public  orphelin_adapter(Context mCtx, List<Orphelin> orphelins) {
        this.mCtx = mCtx;
        this.orphelins = orphelins;
     //   fullList = new ArrayList<>(orphelins);
       // fullList = orphelins;
    }
    public  orphelin_adapter(Context mCtx, List<Orphelin> orphelins, List<Orphelin> fullList) {
        this.mCtx = mCtx;
        this.orphelins = orphelins;
        this.fullList = fullList;
    }

    @Override
    public OrphilanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mCtx).inflate(R.layout.orphilan_item,parent,false);
        OrphilanViewHolder viewHolder = new OrphilanViewHolder(v,mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(OrphilanViewHolder holder, int position) {
        Orphelin orphelin = orphelins.get(position);
        holder.txtNom.setText(orphelin.getNom());
        holder.txtPrenom.setText(orphelin.getPrenom());
  /*      holder.txtAge.setText(String.valueOf(orphelin.getAge()));
        holder.txtCinMere.setText(orphelin.getCin_mere());
        holder.txtNomMere.setText(orphelin.getNom_mere());
        holder.txtTel.setText(String.valueOf(orphelin.getTel()));
        holder.txtNiveauScolaire.setText(orphelin.getNiveau_scolaire());
        holder.txtAddress.setText(orphelin.getAdresse());*/
    }

    @Override
    public int getItemCount() {
        return orphelins.size();
    }

    public static class OrphilanViewHolder extends RecyclerView.ViewHolder{
        TextView txtNom,txtPrenom,txtAge,txtTel,txtNomMere,txtCinMere,txtNiveauScolaire,txtAddress;
        public OrphilanViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            txtNom = (TextView)itemView.findViewById(R.id.textViewNomOrph);
            txtPrenom = (TextView)itemView.findViewById(R.id.textViewPrenomOrph);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.onItemClickListener(position);

                        }
                    }
                }
            });
        }
    }



    @Override
    public Filter getFilter() {
        return orphFilter;
    }


    private Filter orphFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Orphelin> filteredList = new ArrayList<>();
            if(charSequence == null || charSequence.length()==0){
                filteredList.addAll(fullList);
            }else{
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for(Orphelin item:fullList){
                    if(item.getNom().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
        /*    Toast.makeText(mCtx,"size1 = "+filteredList.size(),Toast.LENGTH_LONG).show();
            Toast.makeText(mCtx,"size1 fulllist = "+fullList.size(),Toast.LENGTH_LONG).show();
            Toast.makeText(mCtx,"size1 orphs = "+orphelins.size(),Toast.LENGTH_LONG).show();
          */  FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
         /*   Toast.makeText(mCtx,"size2 = "+filterResults.count,Toast.LENGTH_LONG).show();
            Toast.makeText(mCtx,"size2 fulllist = "+fullList.size(),Toast.LENGTH_LONG).show();
            Toast.makeText(mCtx,"size2 orphs = "+orphelins.size(),Toast.LENGTH_LONG).show();
*/

            orphelins.clear();
            orphelins.addAll((List)filterResults.values);
            notifyDataSetChanged();
        }
    };
}
