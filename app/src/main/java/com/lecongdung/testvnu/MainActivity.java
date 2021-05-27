package com.lecongdung.testvnu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.lecongdung.testvnu.view.HomeActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}