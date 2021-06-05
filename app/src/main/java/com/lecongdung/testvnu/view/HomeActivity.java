package com.lecongdung.testvnu.view;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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
import com.lecongdung.testvnu.R;
import com.lecongdung.testvnu.adapter.KyThiAdapter;
import com.lecongdung.testvnu.model.Kythi;
import com.lecongdung.testvnu.remote.DataClient;
import com.lecongdung.testvnu.remote.DataService;
import com.lecongdung.testvnu.utils.BottomNavigationViewHelper;
import com.lecongdung.testvnu.utils.OreoAndAboveNotification;

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

    private List<Kythi> mListKythi = null;
    private KyThiAdapter myAdapter;
    private DataService mService;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupBottomNavigationView();
        mService = DataClient.getDataClient();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        listenUpdate();
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

    private void listenUpdate() {
        mDatabase.child("notifications").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String title = (String) snapshot.child("title").getValue();
                String body = (String) snapshot.child("body").getValue();
                boolean isUpdate = (boolean) snapshot.child("isUpdate").getValue();
                if (isUpdate) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        sendOAndAboveNotification(title, body);
                    } else {
                        sendNormalNotification(title, body);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void sendNormalNotification(String title, String body) {
        Intent intent = new Intent(this, HomeActivity.class);

        int i = 10;
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pIntent = PendingIntent.getActivity(this, i, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = null;

        builder = new NotificationCompat.Builder(this)
                .setContentText(body)
                .setContentTitle(title)
                .setAutoCancel(true)
                .setPriority(PRIORITY_HIGH)
                .setSound(soundUri)
                .setContentIntent(pIntent)
                .setSmallIcon(R.drawable.logo)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(i, builder.build());
    }

    private void sendOAndAboveNotification(String title, String body) {

        Intent intent = new Intent(this, HomeActivity.class);

        int i = 10;
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pIntent = PendingIntent.getActivity(this, i, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        OreoAndAboveNotification notification1 = new OreoAndAboveNotification(this);
        Notification.Builder builder = notification1.getONotifications(title, body, pIntent, soundUri);

        notification1.getManager().notify(i, builder.build());
    }
}