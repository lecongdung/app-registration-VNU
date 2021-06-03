package com.lecongdung.testvnu.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Telephony;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lecongdung.testvnu.R;
import com.lecongdung.testvnu.adapter.KyThiAdapter;
import com.lecongdung.testvnu.model.Kythi;
import com.lecongdung.testvnu.remote.DataClient;
import com.lecongdung.testvnu.remote.DataService;
import com.lecongdung.testvnu.utils.BottomNavigationViewHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    private static final int ACTIVITY_NUMBER = 0;

    private BottomNavigationView bottomNavigationView;

    private ImageView btn_notification;
    private SearchView search_kythi;
    private RecyclerView rv_kythi;
    private RelativeLayout mNoResultsFoundLayout;

    private List<Kythi> mListKythi = null;
    private KyThiAdapter myAdapter;
    private DataService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupBottomNavigationView();
        mService = DataClient.getDataClient();

        initWeight();
        init();
    }

    private void setupBottomNavigationView() {
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.enableNavigation(this, bottomNavigationView);

        //Change current highlighted icon
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUMBER);
        menuItem.setChecked(true);
    }


    private void initWeight() {
        btn_notification = (ImageView) this.findViewById(R.id.btn_notification);
        search_kythi = (SearchView) this.findViewById(R.id.search_kythi);
        rv_kythi = (RecyclerView) findViewById(R.id.recycler_menu);
        mNoResultsFoundLayout = (RelativeLayout) findViewById(R.id.noResultsFoundLayout);

    }

    private void init() {
        btn_notification.setOnClickListener(v -> {
            Intent intent = new Intent(this, NotificationActivity.class);
            startActivity(intent);
        });

        mService.GetAllKyThi()
                .enqueue(new Callback<List<Kythi>>() {
                    @Override
                    public void onResponse(Call<List<Kythi>> call, Response<List<Kythi>> response) {
                        if(response.isSuccessful()) {
                            List<Kythi> listKythi = response.body();
                            initList(listKythi);
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Kythi>> call, Throwable t) {

                    }
                });

        search_kythi.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                findKythi(newText);
                return false;
            }
        });
    }

    private void initList(List<Kythi> listKythi) {
        mNoResultsFoundLayout.setVisibility(View.INVISIBLE);
        rv_kythi.setHasFixedSize(true);
        rv_kythi.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new KyThiAdapter(HomeActivity.this, listKythi);
        rv_kythi.setAdapter(myAdapter);
        mListKythi = listKythi;
    }

    private void findKythi(String query) {
        ArrayList<Kythi> listResult = new ArrayList();
        if(query.isEmpty()) {
            listResult.addAll(mListKythi);
        }
        else {
            for (Kythi kythi : mListKythi) {
                if(kythi.getMaKythi() != null && !kythi.getMaKythi().isEmpty())  {
                    if(kythi.getMaKythi().contains(query)) {
                        listResult.add(kythi);
                    }
                }
            }
        }
        myAdapter = new KyThiAdapter(HomeActivity.this, listResult);
        rv_kythi.swapAdapter(myAdapter,false);
    }


    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Ấn lần nữa để thoát", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}