package com.lecongdung.testvnu.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.lecongdung.testvnu.R;
import com.lecongdung.testvnu.adapter.KyThiAdapter;
import com.lecongdung.testvnu.adapter.MyKyThiAdapter;
import com.lecongdung.testvnu.adapter.NotificationAdapter;
import com.lecongdung.testvnu.model.Kythi;
import com.lecongdung.testvnu.model.MyKyThi;
import com.lecongdung.testvnu.model.ThongBao;
import com.lecongdung.testvnu.remote.DataClient;
import com.lecongdung.testvnu.remote.DataService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationActivity extends AppCompatActivity {

    private ImageView btn_back;

    private RecyclerView mRecyclerView;

    private List<ThongBao> thongBaoList = null;
    private NotificationAdapter myAdapter;

    private DataService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        mService = DataClient.getDataClient();
        initWeight();
        initContent();
        init();
    }


    private void initWeight() {
        btn_back = (ImageView) this.findViewById(R.id.backArrowPickupRide);
        mRecyclerView = this.findViewById(R.id.recycler_notification);
    }
    private void initContent() {
        mService.GetNotification()
                .enqueue(new Callback<List<ThongBao>>() {
                    @Override
                    public void onResponse(Call<List<ThongBao>> call, Response<List<ThongBao>> response) {
                        if (response.isSuccessful()) {
                            List<ThongBao> result = response.body();
                            thongBaoList = result;
                            initList(result);
                        }
                        else {
                            Toast.makeText(NotificationActivity.this,"Lấy thông báo thất bại",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ThongBao>> call, Throwable t) {
                        Toast.makeText(NotificationActivity.this,"Lỗi lấy thông báo",Toast.LENGTH_SHORT).show();

                    }
                });
    }

    private void init() {

        btn_back.setOnClickListener(v -> {
            finish();
        });
    }

    private void initList(List<ThongBao> listKythi) {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new NotificationAdapter(NotificationActivity.this, listKythi);
        mRecyclerView.setAdapter(myAdapter);
    }

}