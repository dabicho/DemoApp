package com.code3e.demoapp;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.code3e.demoapp.adapters.IntroViewPagerAdapter;
//import com.code3e.demoapp.animations.ZoomOutPageTransformer;
import com.code3e.demoapp.models.IntroPage;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private IntroViewPagerAdapter  introViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        Button introButton = (Button) findViewById(R.id.introButton);
        introButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(IntroActivity.this, loginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        viewPager = (ViewPager)findViewById(R.id.introViewPager);

        List<IntroPage> introPages = new ArrayList<>(4);

        introPages.add(new IntroPage("Bienvenido a la app!",
                getResources().getColor(R.color.colorClearGreen),
                R.drawable.intro_box));
        introPages.add(new IntroPage("Podrás Chatear!...",
                getResources().getColor(R.color.colorLightOrange),
                R.drawable.intro_chat));
        introPages.add(new IntroPage("...Y hacer gestos!",
                getResources().getColor(R.color.colorPastelViolet),
                R.drawable.intro_smiley));
        introPages.add(new IntroPage("Buena suerte!",
                getResources().getColor(R.color.colorYellow),
                R.drawable.intro_done));

        introViewPagerAdapter = new IntroViewPagerAdapter(getSupportFragmentManager(), introPages);

        viewPager.setAdapter(introViewPagerAdapter);
        //viewPager.setPageTransformer(true, new ZoomOutPageTransformer());

        //viewPager.setPageTransformer(true, new com.ToxicBakery.viewpager.transforms.AccordionTransformer());
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

    }
}
