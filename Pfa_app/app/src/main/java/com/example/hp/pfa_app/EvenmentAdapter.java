package com.example.hp.pfa_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
 * Created by hp on 01/05/2021.
 */

public class EvenmentAdapter  extends RecyclerView.Adapter<EvenmentAdapter.EvementHolder>{
    Context mCtx;
    List<Evenment> evenments;
    boolean visibility;

    public EvenmentAdapter(Context mCtx, List<Evenment> evenments,boolean visibility) {
        this.mCtx = mCtx;
        this.evenments = evenments;
        this.visibility = visibility;
    }

    @Override
    public EvementHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.evenment_item,parent,false);
        return new EvementHolder(v);
    }

    @Override
    public void onBindViewHolder(final EvementHolder holder, final int position) {
        final Evenment  evenment = evenments.get(position);
        holder.txtNom.setText(evenment.getNom());
        holder.txtDate.setText(evenment.getDate());
        holder.txtDescription.setText(evenment.getDescription());
        holder.txtResponsabilite.setText(evenment.getResponsable());
        holder.txtContact.setText(evenment.getContact());
        holder.txtAddress.setText(evenment.getLieu());
        Picasso.with(mCtx).load("http://192.168.1.6:82/pfaapp/evenments/images/"+evenment.getImage()).into(holder.imageView);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(holder.linearLayout1.getVisibility()==LinearLayout.VISIBLE){
                    holder.linearLayout1.setVisibility(View.GONE);
                    holder.linearLayout2.setVisibility(View.GONE);
                    holder.linearLayout3.setVisibility(View.GONE);
                    holder.linearLayout4.setVisibility(View.GONE);
                    holder.linearLayout5.setVisibility(View.GONE);
                    holder.button.setText("Show More");
                }else
                if(holder.linearLayout1.getVisibility()==LinearLayout.GONE){
                    holder.linearLayout1.setVisibility(View.VISIBLE);
                    holder.linearLayout2.setVisibility(View.VISIBLE);
                    holder.linearLayout3.setVisibility(View.VISIBLE);
                    holder.linearLayout4.setVisibility(View.VISIBLE);
                    holder.linearLayout5.setVisibility(View.VISIBLE);
                    holder.button.setText("Show Less");

                }
            }
        });


        if(!visibility){
            holder.supprimer.setVisibility(View.GONE);
            holder.editer.setVisibility(View.GONE);
        }
        holder.supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                StringRequest stringRequest = new StringRequest(Request.Method.GET,"http://192.168.1.6:82/pfaapp/evenments/supprimer.php?id="+evenment.getId(), new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }


                }
                ){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("id",String.valueOf(evenment.getId()));
                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
                requestQueue.add(stringRequest);

            }
        });

        holder.editer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), ModifierEvenement.class);
                Bundle b = new Bundle();
                b.putString("id",String.valueOf(evenment.getId()));
                b.putString("nom",String.valueOf(evenment.getNom()));
                b.putString("description",String.valueOf(evenment.getDescription()));
                b.putString("contact",String.valueOf(evenment.getContact()));
                b.putString("date",String.valueOf(evenment.getDate()));
                b.putString("lieu",String.valueOf(evenment.getLieu()));
                b.putString("responsable",String.valueOf(evenment.getResponsable()));
                b.putString("image",String.valueOf(evenment.getImage()));

                intent.putExtras(b);
                view.getContext().startActivity(intent);
            }
        });

        holder.supprimer.setOnClickListener(new View.OnClickListener() {
            @Override

                    public void onClick(View view) {
                        //  Toast.makeText(getApplicationContext(), ""+id, Toast.LENGTH_SHORT).show();
                        String url="http://192.168.1.6:82/pfaapp/evenments/supprimer.php?id="+evenment.getId();
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
                        RequestQueue requestQueue = Volley.newRequestQueue(mCtx);
                        requestQueue.add(stringRequest);

                notifyItemRemoved(position);
                    }

        });
    }

    @Override
    public int getItemCount() {
        return evenments.size();
    }

    public class EvementHolder extends RecyclerView.ViewHolder{
        TextView txtNom,txtDate,txtAddress,txtContact,txtDescription,txtResponsabilite;
        ImageView imageView;
        Button button,supprimer,editer;
        LinearLayout linearLayout1,linearLayout2,linearLayout3,linearLayout4,linearLayout5;
        public EvementHolder(View itemView) {
            super(itemView);
            txtNom = (TextView)itemView.findViewById(R.id.textViewNomEv);
            txtDate = (TextView)itemView.findViewById(R.id.textViewDateEv);
            txtAddress = (TextView)itemView.findViewById(R.id.textViewAddressEv);
            txtDescription = (TextView)itemView.findViewById(R.id.textViewDescriptionEv);
            txtResponsabilite = (TextView)itemView.findViewById(R.id.textViewResponsableEv);
            txtContact = (TextView)itemView.findViewById(R.id.textViewContactEv);
            imageView = itemView.findViewById(R.id.imageView6);
            button = itemView.findViewById(R.id.buttonShowMore);
            supprimer = itemView.findViewById(R.id.buttonSupprimeEvenment);
            editer = itemView.findViewById(R.id.buttonEditeEvenment);
            linearLayout1 = itemView.findViewById(R.id.lin2);
            linearLayout2 = itemView.findViewById(R.id.lin3);
            linearLayout3 = itemView.findViewById(R.id.lin4);
            linearLayout4 = itemView.findViewById(R.id.lin5);
            linearLayout5 = itemView.findViewById(R.id.lin6);


        }
    }
}
