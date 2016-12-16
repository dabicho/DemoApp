package com.code3e.demoapp;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.code3e.demoapp.adapters.RestaurantDetailViewPagerAdapter;

public class RestaurantDetailActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private RestaurantDetailViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);
        //TODO Cargar Cabecera

        //Todo Cargar páginas

        viewPager = (ViewPager)findViewById(R.id.restaurantDetailViewPager);

    }
}
