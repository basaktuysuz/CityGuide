package com.example.cityguide.Common;

import androidx.appcompat.app.AppCompatActivity;

import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cityguide.HelperClasses.OnBoardingAdapter;
import com.example.cityguide.HelperClasses.OnboardingItem;
import com.example.cityguide.R;
import com.example.cityguide.User.UserDashBoard;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class OnBoarding extends AppCompatActivity {
    private LinearLayout LayoutOnboradingIndıcators;
    private OnBoardingAdapter onboardingAdapter;
    private MaterialButton buttonOnboardingAction;
    private static int SPLASH_TIME_OUT = 5000;
    SharedPreferences mSharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_on_boarding);


        LayoutOnboradingIndıcators = findViewById(R.id.layoutOnboardinIndicators);
        buttonOnboardingAction = findViewById(R.id.buttonOnBoardingAction);

        setupOnboardingItems();

        ViewPager2 onboardingViewPager = findViewById(R.id.onboardingViewPager);
        onboardingViewPager.setAdapter(onboardingAdapter);

        setupOnboradingIndıcators();
        setCurrentOnboradingIndıcators(0);
        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboradingIndıcators(position);
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSharedPref = getSharedPreferences("SharedPref", MODE_PRIVATE);
                boolean isFirstTime = mSharedPref.getBoolean("firstTime", true);

                if (isFirstTime) {
                    SharedPreferences.Editor editor = mSharedPref.edit();
                    editor.putBoolean("firstTime", false);


                    //go to on boarding activity

                } else {
                    Intent intent = new Intent(OnBoarding.this, UserDashBoard.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, SPLASH_TIME_OUT);

        buttonOnboardingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onboardingViewPager.getCurrentItem() + 1 < onboardingAdapter.getItemCount()) {
                    onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem() + 1);
                }//end if
                else {
                    startActivity(new Intent(getApplicationContext(), UserDashBoard.class));
                    finish();
                }

            }
        });

    }

    private void setupOnboardingItems() {
        List<OnboardingItem> onboardingItems = new ArrayList<>();
        OnboardingItem screen1 = new OnboardingItem();
        screen1.setTitle("Search Your Location");
        screen1.setDescription("You can search any place nearby or with-in the specified city.We will display specific or all related shops to match your search.");
        screen1.setImage(R.drawable.search_place);

        OnboardingItem screen2 = new OnboardingItem();
        screen2.setTitle("Make A Call");
        screen2.setDescription(" We provide almost the numbers of all departments and shops registered with us.You can perform bookings as well.");
        screen2.setImage(R.drawable.make_a_call);

        OnboardingItem screen3 = new OnboardingItem();
        screen3.setTitle("Add Missing Place");
        screen3.setDescription(" If you have a shop or something want to be a part of our growing industry then add your place by folowing simple steps. ");
        screen3.setImage(R.drawable.add_missing_place);

        OnboardingItem screen4 = new OnboardingItem();
        screen4.setTitle("Sit Back And Enjoy");
        screen4.setDescription(" We will handle everything for you.As you have the app in your pocket then  you don't have to worry about anything.");
        screen4.setImage(R.drawable.sit_back_and_relax);


        onboardingItems.add(screen1);
        onboardingItems.add(screen2);
        onboardingItems.add(screen3);
        onboardingItems.add(screen4);

        onboardingAdapter = new OnBoardingAdapter(onboardingItems);

    }

    private void setupOnboradingIndıcators() {
        ImageView[] indicators = new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        layoutParams.setMargins(8, 0, 8, 0);
        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.onboarding_indicator_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            LayoutOnboradingIndıcators.addView(indicators[i]);
        }//end for
    }// end setupOnboradingIndıcators()

    private void setCurrentOnboradingIndıcators(int index) {
        int childCount = LayoutOnboradingIndıcators.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) LayoutOnboradingIndıcators.getChildAt(i);
            if (i == index) {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),
                                R.drawable.onboarding_indicator_active)
                );
            }//end if
            else {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_inactive)
                );
            }//end else
        }//end for
        if (index == onboardingAdapter.getItemCount() - 1) {
            buttonOnboardingAction.setText("Lets get started");

        }//end if
        else {
            buttonOnboardingAction.setText("Next");

        }//end else

    }
}//end class