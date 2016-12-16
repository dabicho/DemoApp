package com.code3e.demoapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.code3e.demoapp.models.IntroPage;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentIntro extends Fragment {
    private final String TAG = "FragmentIntro";

    private IntroPage page;

    public FragmentIntro() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_intro, container, false);
        ((TextView)view.findViewById(R.id.introTextView)).setText(page.title);
        view.setBackgroundColor(page.color);
        ((ImageView)view.findViewById(R.id.introImageView)).setImageResource(page.image);
        return view;
    }

    public void setPage(IntroPage page) {
        this.page = page;
    }
}
