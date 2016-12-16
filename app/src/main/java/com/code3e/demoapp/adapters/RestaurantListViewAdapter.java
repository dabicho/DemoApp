package com.code3e.demoapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.code3e.demoapp.R;
import com.code3e.demoapp.models.Restaurante;

import java.util.List;

/**
 * Created by admin on 16/12/16.
 */

public class RestaurantListViewAdapter extends ArrayAdapter<Restaurante> {

    private List<Restaurante> restaurantes;
    private int resource;
    private Context context;

    public RestaurantListViewAdapter(Context context, int resource, List<Restaurante> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.restaurantes = objects;
    }

    @Override
    public int getCount() {
        return restaurantes == null? 0:restaurantes.size();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(resource, null);
        TextView textView = (TextView) view.findViewById(R.id.restaurantName);
        textView.setText(restaurantes.get(position).name);

        textView = (TextView) view.findViewById(R.id.restaurantCategories);
        textView.setText(restaurantes.get(position).categories);

        final ImageView logo;

            logo = (ImageView) view.findViewById(R.id.restaurant_logo);
        Glide
                .with(context)
                .load(restaurantes.get(position).logo)
                .centerCrop()
                .placeholder(R.drawable.rest_icon)
                .crossFade()
                .into(logo);


        return view;
    }
}
