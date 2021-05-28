package com.lecongdung.testvnu.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lecongdung.testvnu.R;

public class SigninActivity extends AppCompatActivity {

    private EditText edt_username,edt_email,edt_password,edt_repassword;
    private Button btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        initWeight();
        initOnClick();
    }

    private void initWeight() {
        edt_email = (EditText) findViewById(R.id.edt_email);
        edt_username = (EditText) findViewById(R.id.edt_username);
        edt_password = (EditText) findViewById(R.id.edt_password);
        edt_repassword = (EditText) findViewById(R.id.edt_repassword);
        btn_signup = (Button) findViewById(R.id.btn_signup);
    }

    private void initOnClick() {
        btn_signup.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.Theme_AppCompat_Light_Dialog_Alert);
            final View customLayout = getLayoutInflater().inflate(R.layout.dialog_input_otp,null);
            builder.setView(customLayout);
            builder.setTitle("Xác nhập email");
            builder.setPositiveButton("Xác nhận", (dialog, which) -> {

            });
            builder.setNegativeButton("Hủy", (dialog, which) -> {
                dialog.dismiss();
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        });
    }

}