package com.code3e.demoapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.code3e.demoapp.FragmentIntro;
import com.code3e.demoapp.models.IntroPage;

import java.util.List;

/**
 * Created by admin on 13/12/16.
 */

public class RestaurantDetailViewPagerAdapter extends FragmentPagerAdapter {


    public RestaurantDetailViewPagerAdapter(FragmentManager fm) {

        super(fm);
    }



    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {

        FragmentIntro fragment = new FragmentIntro();

        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0) {
            return "Menu";
        }
        if(position==1) {
            return "Opiniones";
        }
        return "";
    }
}
