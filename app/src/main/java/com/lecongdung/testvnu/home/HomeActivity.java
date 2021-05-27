package com.lecongdung.testvnu.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lecongdung.testvnu.R;
import com.lecongdung.testvnu.notification.NotificationActivity;
import com.lecongdung.testvnu.utils.BottomNavigationViewHelper;

public class HomeActivity extends AppCompatActivity {
    private static final int ACTIVITY_NUMBER = 0;

    private BottomNavigationView bottomNavigationView;

    private ImageView btn_notification, btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupBottomNavigationView();

        initWeight();
        init();
    }
    private void setupBottomNavigationView(){
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.enableNavigation(this, bottomNavigationView);

        //Change current highlighted icon
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUMBER);
        menuItem.setChecked(true);
    }


    private void initWeight() {
        btn_notification = (ImageView)findViewById(R.id.btn_notification);
        btn_back = (ImageView) findViewById(R.id.backArrowPickupRide);
    }

    private void init() {
        btn_notification.setOnClickListener(v -> {
            Intent intent = new Intent(this, NotificationActivity.class);
            startActivity(intent);
        });
        btn_back.setOnClickListener(v -> {
            finish();
        });
    }


}