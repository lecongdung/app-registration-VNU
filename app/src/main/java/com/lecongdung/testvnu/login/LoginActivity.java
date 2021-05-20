package com.lecongdung.testvnu.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lecongdung.testvnu.R;

public class LoginActivity extends AppCompatActivity {

    private EditText edt_mail,edt_password;
    private Button btn_login;
    private TextView tv_forgot_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initWeigth();
        initOnClick();
    }

    private void initWeigth() {
        edt_mail = (EditText) findViewById(R.id.emailEditText);
        edt_password = (EditText) findViewById(R.id.passwordEditText);
        btn_login = (Button) findViewById(R.id.btn_login);
        tv_forgot_password = (TextView) findViewById(R.id.tv_forgot_password);
    }

    private void initOnClick() {

        btn_login.setOnClickListener(v -> {

        });

        tv_forgot_password.setOnClickListener(v -> {

        });
    }
}