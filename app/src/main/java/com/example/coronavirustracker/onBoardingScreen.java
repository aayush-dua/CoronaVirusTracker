package com.example.coronavirustracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class onBoardingScreen extends AppCompatActivity {
    private ViewPager screenPager;
    introViewPagerAdapter newPageAdapter;

    Button nextButton;
    int nCurrentPage = 0;
    Button getStartedButton;
    Animation btnAnimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding_screen);


        if (restorePrefData()) {
            Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(mainActivity);
            finish();
        } else {

            nextButton = (Button) findViewById(R.id.nextBtn);
            getStartedButton = (Button) findViewById(R.id.getStartedBtn);

            final List<ScreenItem> mList = new ArrayList<>();
            mList.add(new ScreenItem("Go Corona!", "The CoronaVirus tracker", R.drawable.covid19));
            mList.add(new ScreenItem("Daily Case Updates", "Obtain CoronaVirus case updates on the go!", R.drawable.pandemic));
            mList.add(new ScreenItem("Symptom Tracker", "Check our symptom list to see if you feel funny :{", R.drawable.hottemp));
            mList.add(new ScreenItem("Safety Tips", "Tips so that you don't catch the CoronaVirus :D", R.drawable.mask));

            screenPager = findViewById(R.id.screenViewPager);
            newPageAdapter = new introViewPagerAdapter(this, mList);
            screenPager.setAdapter(newPageAdapter);
            btnAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.gobuttonanimation);

            //page change listener

            screenPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    nCurrentPage = position;
                    if (nCurrentPage == mList.size() - 1) {
                        loadLastScreen();
                    }

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

            //button functionality

            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    screenPager.getCurrentItem();
                    if (nCurrentPage < mList.size() ) {

                        nCurrentPage++;
                        screenPager.setCurrentItem(nCurrentPage);

                    }
                    if (nCurrentPage == mList.size() - 1) {

                        loadLastScreen();
                    }

                }
            });


            getStartedButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(mainActivity);
                    savePrefData();
                    finish();
                }
            });
        }
    }

    private boolean restorePrefData() {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("package com.example.coronavirustracker",MODE_PRIVATE);
        Boolean activityOpenBefore = pref.getBoolean("isIntroOpened",false);
        return activityOpenBefore;
    }

    private void savePrefData() {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("package com.example.coronavirustracker",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpened",true);
        editor.commit();


  }

    private void loadLastScreen() {
        if(getStartedButton.getVisibility() == View.INVISIBLE) {
            getStartedButton.setVisibility(View.VISIBLE);
            getStartedButton.setAnimation(btnAnimation);
        }
        nextButton.setVisibility(View.INVISIBLE);

        //setButton Animation



    }

}
