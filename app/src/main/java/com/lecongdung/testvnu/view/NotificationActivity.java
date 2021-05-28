package com.lecongdung.testvnu.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.lecongdung.testvnu.R;

public class NotificationActivity extends AppCompatActivity {

    private ImageView btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        initWeight();
        init();
    }

    private void initWeight() {
        btn_back = (ImageView) this.findViewById(R.id.backArrowPickupRide);
    }

    private void init() {

        btn_back.setOnClickListener(v -> {
            finish();
        });
    }

}