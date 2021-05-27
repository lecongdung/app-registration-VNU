package com.lecongdung.testvnu.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lecongdung.testvnu.R;
import com.lecongdung.testvnu.utils.BottomNavigationViewHelper;

public class MyTestActivity extends AppCompatActivity {
    private static final int ACTIVITY_NUMBER = 1;

    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_test);
        setupBottomNavigationView();

    }

    private void setupBottomNavigationView(){
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.enableNavigation(this, bottomNavigationView);

        //Change current highlighted icon
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUMBER);
        menuItem.setChecked(true);
    }

}