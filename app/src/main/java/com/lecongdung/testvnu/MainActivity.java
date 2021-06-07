package com.lecongdung.testvnu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lecongdung.testvnu.common.Common;
import com.lecongdung.testvnu.data.SessionManager;
import com.lecongdung.testvnu.model.Student;
import com.lecongdung.testvnu.remote.DataClient;
import com.lecongdung.testvnu.remote.DataService;
import com.lecongdung.testvnu.remote.entity.BodyLogin;
import com.lecongdung.testvnu.view.HomeActivity;
import com.lecongdung.testvnu.view.LoginActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class
MainActivity extends AppCompatActivity {

    private SessionManager sessionManager;
    private DataService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mService = DataClient.getDataClient();
        sessionManager = new SessionManager(getApplication());

        if(sessionManager.Check()) {
            BodyLogin bodyLogin = sessionManager.getBodyLogin();
            mService.Login(bodyLogin)
                    .enqueue(new Callback<Student>() {
                        @Override
                        public void onResponse(Call<Student> call, Response<Student> response) {
                            if (response.isSuccessful()) {
                                Common.mStudent = response.body();
                                startHomeActivity();
                            }
                            else {
                                Toast.makeText(MainActivity.this, "Đăng nhập tự động không thành công", Toast.LENGTH_SHORT).show();
                                startLoginActivity();
                            }
                        }

                        @Override
                        public void onFailure(Call<Student> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "Đăng nhập tự động không thành công", Toast.LENGTH_SHORT).show();
                            startLoginActivity();
                            Log.e("Lỗi đăng nhập", t.getMessage());
                        }
                    });
        }
        else {
            startLoginActivity();
        }

    }

    private void startLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
    public void startHomeActivity() {
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}