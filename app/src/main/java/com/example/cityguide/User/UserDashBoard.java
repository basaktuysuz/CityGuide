package com.example.cityguide.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.cityguide.Common.LoginSignup.RetailerStartUpScreen;
import com.example.cityguide.HelperClasses.HomeAdapter.CategoriesAdapter;
import com.example.cityguide.HelperClasses.HomeAdapter.CategoriesHelperClass;
import com.example.cityguide.HelperClasses.HomeAdapter.FeaturedAdapter;
import com.example.cityguide.HelperClasses.HomeAdapter.MostviewAdapter;
import com.example.cityguide.HelperClasses.HomeAdapter.FeaturedHelperClass;
import com.example.cityguide.HelperClasses.HomeAdapter.MostviewHelperClass;
import com.example.cityguide.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class UserDashBoard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //Variables
    static final float END_SCALE = 0.7f;
    RecyclerView featureRecycler;
    RecyclerView mostviewRecycler;
    RecyclerView categoriesRecycler;
    RecyclerView.Adapter adapter;
    LinearLayout contentView;

    //Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menuIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_dash_board);
        //hooks
        featureRecycler = findViewById(R.id.featured_recycler);
        featuredRecycler();

        mostviewRecycler = findViewById(R.id.mostviewed_recycler);
        mostviewedRecycler();

        categoriesRecycler = findViewById(R.id.categories_recycler);
        categoriesRecycler();

        //menu hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.content);

        navigationDrawer();

    }//end method

    //navigation Drawer functions
    private void navigationDrawer() {

        //Navigation Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }

            }
        });
        animateNavigationDrawer();

    }//end method

    private void animateNavigationDrawer() {
        drawerLayout.setScrimColor(getResources().getColor(R.color.card3));
        //Add any color or remove it to use the default one!
        //To make it transparent use Color.Transparent in side setScrimColor();
        //drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);
                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_all_categories:
                startActivity(new Intent(getApplicationContext(), AllCategories.class));
                break;
        }

        return true;
    }//end method


    private void featuredRecycler() {
        featureRecycler.setHasFixedSize(true);
        featureRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        //pass array to adapter
        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();
        featuredLocations.add(new FeaturedHelperClass(R.drawable.mcdonalds_img, "Mcdonald's", "Mc sadasdasd asdasdsad asdasd "));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.city_1, "Edenrobe", "Mc swıeuen asdasdsad asdasd "));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.city_2, "Sweet & Bakers", "oıqeyduıbd sdasdsad asdasd "));

        adapter = new FeaturedAdapter(featuredLocations);
        featureRecycler.setAdapter(adapter);

        GradientDrawable gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600});
    }// end method


    private void mostviewedRecycler() {
        mostviewRecycler.setHasFixedSize(true);
        mostviewRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<MostviewHelperClass> mostviewedLocations = new ArrayList<>();
        mostviewedLocations.add(new MostviewHelperClass(R.drawable.pizzalocal_img, "Pizza Locale", "pizza alsdöasldöasdl "));
        mostviewedLocations.add(new MostviewHelperClass(R.drawable.burgerattack_image, "Burger Attack", "Delicious burgers "));
        mostviewedLocations.add(new MostviewHelperClass(R.drawable.city_2, "Sweet & Bakers", "oıqeyduıbd sdasdsad asdasd "));
        adapter = new MostviewAdapter(mostviewedLocations);
        mostviewRecycler.setAdapter(adapter);
    }//end method

    public void categoriesRecycler() {
        categoriesRecycler.setHasFixedSize(true);
        categoriesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<CategoriesHelperClass> categoryLocations = new ArrayList<>();
        categoryLocations.add(new CategoriesHelperClass(R.drawable.restaurant_img, "Restaurant"));
        categoryLocations.add(new CategoriesHelperClass(R.drawable.hospital_image_, "Hospital"));

        adapter = new CategoriesAdapter(categoryLocations);
        categoriesRecycler.setAdapter(adapter);

    }//end method


    //normal functions
    public void callRetailerScreens(View view) {
        startActivity(new Intent(getApplicationContext(), RetailerStartUpScreen.class));
    }
}

