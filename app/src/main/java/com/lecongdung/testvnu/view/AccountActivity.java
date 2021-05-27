package com.lecongdung.testvnu.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lecongdung.testvnu.R;
import com.lecongdung.testvnu.utils.BottomNavigationViewHelper;

public class AccountActivity extends AppCompatActivity {
    private static final int ACTIVITY_NUMBER = 2;

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
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