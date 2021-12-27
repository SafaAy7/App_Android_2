package com.example.hp.pfa_app;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by hp on 12/05/2021.
 */

public class ViewPagerEvenment extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<Evenment> evenments;

    public ViewPagerEvenment(Context context,ArrayList<Evenment> evenments) {
        this.context = context;
        this.evenments = evenments;
    }

    @Override
    public int getCount() {
        return evenments.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Evenment evenment = evenments.get(position);
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.evenment_item,null);
        ImageView imageView=(ImageView)view.findViewById(R.id.imageView6);
        TextView txtvNom = view.findViewById(R.id.textViewNomEv);
        TextView txtDate = view.findViewById(R.id.textViewDateEv);
        TextView txtvContact = view.findViewById(R.id.textViewContactEv);
        TextView txtvDescription = view.findViewById(R.id.textViewDescriptionEv);
        TextView txtvAddress = view.findViewById(R.id.textViewAddressEv);
        TextView txtvResponsable = view.findViewById(R.id.textViewResponsableEv);

        Picasso.with(context).load("").into(imageView);
        txtvNom.setText(evenment.getNom());
        txtDate.setText(evenment.getDate());
        txtvDescription.setText(evenment.getDescription());
        txtvContact.setText(evenment.getContact());
        txtvResponsable.setText(evenment.getResponsable());
        txtvAddress.setText(evenment.getLieu());

        ViewPager viewPager = (ViewPager)container;
        viewPager.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        ViewPager viewPager = (ViewPager)container;
        View view = (View)object;
        viewPager.removeView(view);

    }
}
