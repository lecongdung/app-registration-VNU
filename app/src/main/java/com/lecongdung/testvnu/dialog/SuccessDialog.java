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
import com.lecongdung.testvnu.view.MyTestActivity;

public class SuccessDialog extends Dialog  {

    public Context context;
    private AppCompatActivity activity;
    private int flatPhuongthuc;

    private TextView tv_message, btn_ok;


    public SuccessDialog(@NonNull Context context,AppCompatActivity activity, int flatPhuongthuc ) {
        super(context);
        this.context = context;
        this.activity = activity;
        this.flatPhuongthuc = flatPhuongthuc;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_success_dangkythi);
        
        initWeight();
        initOnClick();
    }


    private void initWeight() {
        tv_message = (TextView) findViewById(R.id.message);
        btn_ok = (TextView) findViewById(R.id.btn_ok);

        if(flatPhuongthuc == 1) {
            tv_message.setText("Bạn hãy chuyển khoản với lời ghi chú với cấu trúc:\n" +
                    "Tendangnhap_email_makythi");
        }
    }

    private void initOnClick() {
        btn_ok.setOnClickListener(v -> {
            dismiss();
            Intent intent = new Intent(context, MyTestActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);

        });
    }

}
