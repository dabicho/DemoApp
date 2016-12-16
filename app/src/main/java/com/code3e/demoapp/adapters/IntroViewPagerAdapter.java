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

public class IntroViewPagerAdapter extends FragmentPagerAdapter {

    private List<IntroPage> pages;

    public IntroViewPagerAdapter(FragmentManager fm, List<IntroPage> pages) {

        super(fm);
        this.pages = pages;
    }

    public void setPages(List<IntroPage> pages) {
        this.pages = pages;
    }

    @Override
    public int getCount() {
        return pages==null?0:pages.size();
    }

    @Override
    public Fragment getItem(int position) {

        FragmentIntro fragment = new FragmentIntro();
        fragment.setPage(pages.get(position));

        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Página "+position;
    }
}
