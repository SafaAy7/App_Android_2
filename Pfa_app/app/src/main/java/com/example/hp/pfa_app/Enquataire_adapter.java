package com.example.hp.pfa_app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hp on 16/04/2021.
 */

public class Enquataire_adapter extends RecyclerView.Adapter<Enquataire_adapter.EnqueteurViewHolder> {

    Context ctx;
    List<Enquataire> enquataires;
    OnItemClickListener mListener;

    interface OnItemClickListener{
       void onItemClick(int posistion);
    }

    void OnItemClickListener(OnItemClickListener onItemListener){mListener=onItemListener;}

    public Enquataire_adapter(Context ctx, List<Enquataire> enquataires) {
        this.ctx = ctx;
        this.enquataires = enquataires;

    }

    @Override
    public EnqueteurViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.enqueteur,parent,false);
        EnqueteurViewHolder enqueteurViewHolder = new EnqueteurViewHolder(v,mListener);
        return enqueteurViewHolder;
    }

    @Override
    public void onBindViewHolder(EnqueteurViewHolder holder, final int position) {
        final Enquataire enquataire = enquataires.get(position);
        holder.nom.setText(enquataire.getNom());
        holder.prenom.setText(enquataire.getPrenom());
        holder.cin.setText(enquataire.getCin());
        holder.tel.setText(enquataire.getTel());
        holder.email.setText(enquataire.getEmail());
        holder.address.setText(enquataire.getAdresse());

        holder.but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Toast.makeText(getApplicationContext(), ""+id, Toast.LENGTH_SHORT).show();
               String url="http://192.168.1.6:82/pfaapp/enquaitaire/supprimer.php?id="+enquataire.getId();
                StringRequest stringRequest = new StringRequest(Request.Method.GET,url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //     Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }){
                    /*
                                @Override
                                public Map<String, String> getHeaders() throws AuthFailureError {
                                    Map<String,String> headers = new HashMap<>();
                                    headers.put("content-type","text/json");
                                    return headers;
                                }
                    */
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<>();
                       ;
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(ctx);
                requestQueue.add(stringRequest);
                notifyItemRemoved(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return enquataires.size();
    }

    public class EnqueteurViewHolder extends RecyclerView.ViewHolder{

        TextView nom,prenom,cin,email,tel,address;
        ImageView imageView;
        Button but,but1;


        public EnqueteurViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            nom = (TextView)itemView.findViewById(R.id.textViewNom);
            prenom = (TextView)itemView.findViewById(R.id.textViewPrenom);
            cin = (TextView)itemView.findViewById(R.id.textViewCin);
            tel = (TextView)itemView.findViewById(R.id.textViewTel);
            email = (TextView)itemView.findViewById(R.id.textViewEmail);
            address = (TextView)itemView.findViewById(R.id.textViewAddress);
            imageView = (ImageView) itemView.findViewById(R.id.imageView3);
            but = (Button) itemView.findViewById(R.id.but);
            but1 =(Button) itemView.findViewById(R.id.but1);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View view) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });






        }

    }
}
