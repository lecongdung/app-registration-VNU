package com.lecongdung.testvnu.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lecongdung.testvnu.R;
import com.lecongdung.testvnu.view.NotificationActivity;
import com.lecongdung.testvnu.utils.BottomNavigationViewHelper;

public class HomeActivity extends AppCompatActivity {
    private static final int ACTIVITY_NUMBER = 0;

    private BottomNavigationView bottomNavigationView;

    private ImageView btn_notification;
    private ImageButton btn_search;
    private EditText edt_search_name;
    private AutoCompleteTextView edt_search_semester;

    private String[] arr_semester= {"HK II 20-21","HK I 20-21","HK II 19-20","HK I 19-20","HK II 18-19","HK I 18-19"};

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
        btn_notification = (ImageView) this.findViewById(R.id.btn_notification);
        btn_search = (ImageButton) this.findViewById(R.id.btn_search);
        edt_search_name = (EditText) this.findViewById(R.id.edt_search_name);
        edt_search_semester = (AutoCompleteTextView) this.findViewById(R.id.edt_search_semester);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, arr_semester);
        edt_search_semester.setThreshold(1);
        edt_search_semester.setAdapter(adapter);
    }

    private void init() {
        btn_notification.setOnClickListener(v -> {
            Intent intent = new Intent(this, NotificationActivity.class);
            startActivity(intent);
        });
        btn_search.setOnClickListener(v -> {

        });
        edt_search_semester.setOnClickListener(v -> {
            edt_search_semester.showDropDown();
        });
    }


}