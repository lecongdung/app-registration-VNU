package com.lecongdung.testvnu.view;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.RemoteMessage;
import com.lecongdung.testvnu.R;
import com.lecongdung.testvnu.adapter.KyThiAdapter;
import com.lecongdung.testvnu.common.Common;
import com.lecongdung.testvnu.fcm.Notification;
import com.lecongdung.testvnu.model.Kythi;
import com.lecongdung.testvnu.remote.DataClient;
import com.lecongdung.testvnu.remote.DataService;
import com.lecongdung.testvnu.utils.BadgeView;
import com.lecongdung.testvnu.utils.BottomNavigationViewHelper;
import com.lecongdung.testvnu.utils.OreoAndAboveNotification;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.firebase.messaging.RemoteMessage.PRIORITY_HIGH;

public class HomeActivity extends AppCompatActivity {
    private static final int ACTIVITY_NUMBER = 0;

    private BottomNavigationView bottomNavigationView;

    private ImageView btn_notification;
    private SearchView search_kythi;
    private RecyclerView rv_kythi;
    private RelativeLayout mNoResultsFoundLayout;
    private Context mContext = HomeActivity.this;

    private List<Kythi> mListKythi = null;
    private KyThiAdapter myAdapter;
    private DataService mService;

    private BadgeView badgeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupBottomNavigationView();
        mService = DataClient.getDataClient();
        initWeight();
        badgeView = new BadgeView(mContext, btn_notification);
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
                if(mListKythi != null) {
                    if(!mListKythi.isEmpty())
                        findKythi(newText);
                }
                return false;
            }
        });
    }

    private void initList(List<Kythi> listKythi) {
        if(!listKythi.isEmpty()) {
            mNoResultsFoundLayout.setVisibility(View.INVISIBLE);
            rv_kythi.setHasFixedSize(true);
            rv_kythi.setLayoutManager(new LinearLayoutManager(this));
            myAdapter = new KyThiAdapter(HomeActivity.this, listKythi);
            rv_kythi.setAdapter(myAdapter);
            mListKythi = listKythi;
        }
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


    private void setupBadge(int reminderLength){
        if (reminderLength > 0){
            //Adds badge to the notification imageview on the toolbar
            badgeView.setTextSize(10);
            badgeView.setTextColor(Color.parseColor("#ffffff"));
            badgeView.setBadgeBackgroundColor(Color.parseColor("#ff0000"));
            badgeView.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
            badgeView.setText(String.valueOf(reminderLength));
            badgeView.setBadgeMargin(5, 0);
            badgeView.show();

        } else {
            badgeView.hide();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(RemoteMessage notification){
        setupBadge(Common.number);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        setupBadge(Common.number);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

}