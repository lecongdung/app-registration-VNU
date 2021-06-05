package com.lecongdung.testvnu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.lecongdung.testvnu.R;
import com.lecongdung.testvnu.common.Common;
import com.lecongdung.testvnu.model.Kythi;
import com.lecongdung.testvnu.model.Lephi;
import com.lecongdung.testvnu.model.MyKyThi;
import com.lecongdung.testvnu.remote.DataClient;
import com.lecongdung.testvnu.remote.DataService;
import com.lecongdung.testvnu.remote.entity.BodyLePhiUpdate;
import com.lecongdung.testvnu.view.DangKyThiActivity;
import com.lecongdung.testvnu.view.MyTestActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlertDialog extends Dialog {

    public Context context;
    private AppCompatActivity activity;
    private Kythi mKythi;
    private MyKyThi mMyKyThi;
    private String message;

    private DataService mService;


    private TextView btn_ok, btn_thoat, tv_message;

    public AlertDialog(@NonNull Context context, Kythi mKythi) {
        super(context);
        this.context = context;
        this.mKythi = mKythi;
    }

    public AlertDialog(@NonNull Context context, AppCompatActivity activity, MyKyThi mMyKyThi, String message) {
        super(context);
        this.context = context;
        this.activity = activity;
        this.mMyKyThi = mMyKyThi;
        this.message = message;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_alert_dangkythi);
        mService = DataClient.getDataClient();

        initWeight();
        initOnClick();
    }

    private void initWeight() {
        btn_ok = (TextView) findViewById(R.id.btn_ok);
        btn_thoat = (TextView) findViewById(R.id.btn_thoat);
        tv_message = (TextView) findViewById(R.id.message);

        if (message != null && !message.isEmpty()) {
            tv_message.setText(message);
        }
    }

    private void initOnClick() {
        btn_ok.setOnClickListener(v -> {
            if (message == null) {
                Intent intent = new Intent(context, DangKyThiActivity.class);
                intent.putExtra("data", mKythi);
                context.startActivity(intent);
                dismiss();
            } else {
                deleteDangKyThi();
                dismiss();
                Intent intent = new Intent(context, MyTestActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);

            }
        });

        btn_thoat.setOnClickListener(v -> {
            dismiss();
        });
    }

    private void deleteDangKyThi() {
        BodyLePhiUpdate body = new BodyLePhiUpdate();
        body.setMakythi("");
        body.setLephidangky(0);
        body.setLephidanop(0);
        body.setStatus("0");
        mService.UpdateLePhi(Common.mStudent.getId() - 1, body)
                .enqueue(new Callback<Lephi>() {
                    @Override
                    public void onResponse(Call<Lephi> call, Response<Lephi> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(context, "Hủy đăng ký thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Hủy đăng ký thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Lephi> call, Throwable t) {
                        Toast.makeText(context, "Hủy đăng ký thất bại", Toast.LENGTH_SHORT).show();
                        Log.e("huy dang ky", t.getMessage());
                    }
                });
    }
}
