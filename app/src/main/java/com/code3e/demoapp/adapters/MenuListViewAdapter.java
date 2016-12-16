package com.code3e.demoapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.code3e.demoapp.R;
import com.code3e.demoapp.models.MenuItem;

import java.util.List;

/**
 * Created by admin on 12/12/16.
 */

public class MenuListViewAdapter extends ArrayAdapter<MenuItem> {

    Context context;
    int resource;
    List<MenuItem> objects;

    public MenuListViewAdapter(Context context, int resource, List<MenuItem> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    //Define el número de filas del listview
    @Override
    public int getCount() {
        return objects.size();
    }

    // Crea el layout de cada fila de listview
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(resource, null);
        TextView textView = (TextView)view.findViewById(R.id.menuItemtitle);
        view.setBackgroundColor(objects.get(position).color);
        textView.setText(objects.get(position).title);
        return view;
    }
}
