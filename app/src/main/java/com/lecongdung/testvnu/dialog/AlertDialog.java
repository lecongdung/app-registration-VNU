package com.lecongdung.testvnu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.lecongdung.testvnu.R;
import com.lecongdung.testvnu.model.Kythi;
import com.lecongdung.testvnu.view.DangKyThiActivity;

public class AlertDialog extends Dialog {

    public Context context;
    private Kythi mKythi;


    private TextView btn_ok, btn_thoat;

    public AlertDialog(@NonNull Context context, Kythi mKythi) {
        super(context);
        this.context = context;
        this.mKythi = mKythi;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_alert_dangkythi);

        initWeight();
        initOnClick();
    }

    private void initWeight() {
        btn_ok  = (TextView) findViewById(R.id.btn_ok);
        btn_thoat  = (TextView) findViewById(R.id.btn_thoat);
    }

    private void initOnClick() {
        btn_ok.setOnClickListener(v -> {
            Intent intent = new Intent(context, DangKyThiActivity.class);
            intent.putExtra("data",mKythi);
            context.startActivity(intent);
            dismiss();
        });

        btn_thoat.setOnClickListener(v -> {
            dismiss();
        });
    }
}
