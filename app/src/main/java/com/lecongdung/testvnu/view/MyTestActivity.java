package com.lecongdung.testvnu.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.lecongdung.testvnu.R;
import com.lecongdung.testvnu.adapter.KyThiAdapter;
import com.lecongdung.testvnu.adapter.MyKyThiAdapter;
import com.lecongdung.testvnu.common.Common;
import com.lecongdung.testvnu.model.Kythi;
import com.lecongdung.testvnu.model.Lephi;
import com.lecongdung.testvnu.model.Monthi;
import com.lecongdung.testvnu.model.MyKyThi;
import com.lecongdung.testvnu.remote.DataClient;
import com.lecongdung.testvnu.remote.DataService;
import com.lecongdung.testvnu.utils.BottomNavigationViewHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyTestActivity extends AppCompatActivity {
    private static final int ACTIVITY_NUMBER = 1;

    private BottomNavigationView bottomNavigationView;

    private TextView tv_title;
    private ImageView btn_notification;
    private SearchView search_kythi;

    private RecyclerView rv_kythi;
    private RelativeLayout mNoResultsFoundLayout;

    private List<MyKyThi> mListKythi = null;
    private MyKyThiAdapter myAdapter;
    private DataService mService;

    private MyKyThi mMyKyThi;
    private Lephi mLephi;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_test);
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

    private void initContent() {
        mService.GetDangKyLePhi(Common.mStudent.getId())
                .enqueue(new Callback<Lephi>() {
                    @Override
                    public void onResponse(Call<Lephi> call, Response<Lephi> response) {
                        if (response.isSuccessful()) {
                            mLephi = response.body();
                            if(mLephi.getMakythi() != null && !mLephi.getMakythi().isEmpty()) {
                                getKythi(mLephi.getMakythi(), mLephi);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Lephi> call, Throwable t) {

                    }
                });
    }

    private void getKythi(String makythi, Lephi lephi) {
        mService.GetAllKyThi()
                .enqueue(new Callback<List<Kythi>>() {
                    @Override
                    public void onResponse(Call<List<Kythi>> call, Response<List<Kythi>> response) {
                        if(response.isSuccessful()) {
                            List<Kythi> kythiList = response.body();
                            for (Kythi kythi :kythiList) {
                                if(kythi.getMaKythi().equals(makythi)) {
                                    createMyKyThi(kythi,lephi);
                                    break;
                                }
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Kythi>> call, Throwable t) {

                    }
                });
    }

    private void createMyKyThi(Kythi kythi, Lephi lephi) {
        MyKyThi myKyThi = new MyKyThi();
        myKyThi.setId(kythi.getId());
        myKyThi.setMaKythi(kythi.getMaKythi());
        myKyThi.setMota(kythi.getMota());
        myKyThi.setTenKythi(kythi.getTenKythi());
        myKyThi.setTungay(kythi.getTungay());
        myKyThi.setToingay(kythi.getToingay());
        myKyThi.setHandangky(kythi.getHandangky());
        myKyThi.setLephidangky(lephi.getLephidangky());
        myKyThi.setLephidanop(lephi.getLephidanop());
        myKyThi.setNguoithu(lephi.getNguoithu());
        myKyThi.setNgaythu(lephi.getNgaythu());
        myKyThi.setStatus(lephi.getStatus());
        myKyThi.setNgaydangky(lephi.getNgaydangky());

        mMyKyThi = myKyThi;

        mListKythi = new ArrayList<>();
        mListKythi.add(myKyThi);

        initList(mListKythi);
    }

    private void initWeight() {
        tv_title = (TextView) this.findViewById(R.id.tv_title);
        btn_notification = (ImageView) this.findViewById(R.id.btn_notification);
        tv_title.setText("Kỳ thi của bạn");
        search_kythi = (SearchView) this.findViewById(R.id.search_kythi);
        rv_kythi = (RecyclerView) findViewById(R.id.recycler_menu);
        mNoResultsFoundLayout = (RelativeLayout) findViewById(R.id.noResultsFoundLayout);

    }

    private void init() {
        btn_notification.setOnClickListener(v -> {
            Intent intent = new Intent(this, NotificationActivity.class);
            startActivity(intent);
        });
        search_kythi.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(mListKythi != null) {
                    if(!mListKythi.isEmpty())
                    findKythi(newText);
                }
                return false;
            }
        });

    }
    private void initList(List<MyKyThi> listKythi) {
        mNoResultsFoundLayout.setVisibility(View.INVISIBLE);
        rv_kythi.setHasFixedSize(true);
        rv_kythi.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyKyThiAdapter(MyTestActivity.this, listKythi);
        rv_kythi.setAdapter(myAdapter);
        mListKythi = listKythi;
    }

    private void findKythi(String query) {
        ArrayList<MyKyThi> listResult = new ArrayList();
        if(query.isEmpty()) {
            listResult.addAll(mListKythi);
        }
        else {
            for (MyKyThi kythi : mListKythi) {
                if(kythi.getMaKythi() != null && !kythi.getMaKythi().isEmpty())  {
                    if(kythi.getMaKythi().contains(query)) {
                        listResult.add(kythi);
                    }
                }
            }
        }
        myAdapter = new MyKyThiAdapter(MyTestActivity.this, listResult);
        rv_kythi.swapAdapter(myAdapter,false);
    }


    private void startHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            startHomeActivity();
            return;
        }

        this.doubleBackToExitPressedOnce = true;

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initContent();

    }
}