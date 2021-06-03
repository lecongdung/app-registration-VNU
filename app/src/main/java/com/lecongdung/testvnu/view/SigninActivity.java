package com.lecongdung.testvnu.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.lecongdung.testvnu.R;
import com.lecongdung.testvnu.common.Common;
import com.lecongdung.testvnu.model.Student;
import com.lecongdung.testvnu.remote.DataClient;
import com.lecongdung.testvnu.remote.DataService;
import com.lecongdung.testvnu.remote.entity.BodyRegister;
import com.lecongdung.testvnu.remote.entity.BodyStudentDelete;
import com.lecongdung.testvnu.remote.entity.BodyStudentUpdatePassword;
import com.lecongdung.testvnu.remote.entity.BodyStudentUpdateVerify;
import com.lecongdung.testvnu.remote.entity.ResponeRegister;
import com.lecongdung.testvnu.remote.entity.ResponeStudentUpdate;
import com.lecongdung.testvnu.remote.entity.User;

import java.io.IOException;
import java.nio.file.Path;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;

public class SigninActivity extends AppCompatActivity {

    private EditText edt_username, edt_email, edt_password, edt_repassword;
    private Button btn_signup;
    private CardView card_view_loading;

    private DataService mService;

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
        card_view_loading = (CardView) findViewById(R.id.card_view_loading);

        mService = DataClient.getDataClient();
    }

    private void initOnClick() {
        btn_signup.setOnClickListener(v -> {
            card_view_loading.setVisibility(View.VISIBLE);
            String email = edt_email.getText().toString().trim();
            String username = edt_username.getText().toString().trim();
            String password = edt_password.getText().toString();
            String repassword = edt_repassword.getText().toString();

            if (checkInput(email, username, password, repassword)) {
                createAccount(email, username, password);
            }
            else {
                card_view_loading.setVisibility(View.GONE);
            }
        });
    }

    private boolean checkInput(String email, String username, String password, String repassword) {
        if (email.equals("") || username.equals("") || password.equals("") || repassword.equals("")) {
            Toast.makeText(SigninActivity.this, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!Common.isValidEmail(email)) {
            Toast.makeText(SigninActivity.this, "Email sai định dạng", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.length() < 6) {
            Toast.makeText(SigninActivity.this, "Mật khẩu phải lớn hơn 6 ký tự", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!password.equals(repassword)) {
            Toast.makeText(SigninActivity.this, "Nhập lại mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void createAccount(String email, String username, String password) {
        BodyRegister bodyRegister = new BodyRegister(username, email, password, false);
        mService.Register(bodyRegister)
                .enqueue(new Callback<ResponeRegister>() {
                    @Override
                    public void onResponse(Call<ResponeRegister> call, Response<ResponeRegister> response) {
                        if (response.isSuccessful()) {
                            ResponeRegister responeRegister = response.body();
                            openDialogOTP(responeRegister);
                        } else{
                            card_view_loading.setVisibility(View.GONE);
                            Toast.makeText(SigninActivity.this, "Có lỗi khi đăng ký", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponeRegister> call, Throwable t) {
                        card_view_loading.setVisibility(View.GONE);
                        Toast.makeText(SigninActivity.this, "Có lỗi khi đăng ký", Toast.LENGTH_SHORT).show();
                        Log.e("Lỗi đăng nhập", t.getMessage());
                    }
                });

    }


    private void openDialogOTP(ResponeRegister responeRegister) {
        card_view_loading.setVisibility(View.GONE);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View customLayout = getLayoutInflater().inflate(R.layout.dialog_input_otp, null);

        ImageView btn_resend = (ImageView) customLayout.findViewById(R.id.btn_resend);
        btn_resend.setOnClickListener(v -> {
            deleteAccount();

            String email = edt_email.getText().toString().trim();
            String username = edt_username.getText().toString().trim();
            String password = edt_password.getText().toString();
            createAccount(email,username,password);
            Toast.makeText(SigninActivity.this, "Đã gửi lại mã xác nhận", Toast.LENGTH_SHORT).show();

        });

        builder.setView(customLayout);
        builder.setTitle("Xác thực tài khoản");
        builder.setPositiveButton("Xác nhận", (dialog, which) -> {
            EditText edt_otp = (EditText) customLayout.findViewById(R.id.edt_otp);
            String otp = edt_otp.getText().toString().trim();
            if (!otp.isEmpty()) {
                if (otp.equals(responeRegister.getOTP())) {
                    verifyAccount(responeRegister.getUser());
                } else {
                    Toast.makeText(SigninActivity.this, "Mã xác nhận không chính xác", Toast.LENGTH_SHORT).show();
                    deleteAccount();
                    dialog.dismiss();
                }
            } else {
                Toast.makeText(SigninActivity.this, "Vui lòng nhập mã xác nhận", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Hủy", (dialog, which) -> {
            deleteAccount();
            dialog.dismiss();
        });

        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    private void deleteAccount() {
        BodyStudentDelete bodyStudentDelete = new BodyStudentDelete(edt_username.getText().toString().trim());
        mService.StudentDelete(bodyStudentDelete)
                .enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if (!response.isSuccessful()) {
                            try {
                                Log.e("Lỗi xóa tài khoản", response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        Log.e("Lỗi xóa tài khoản", t.getMessage());
                    }
                });
    }



    private void verifyAccount(User user) {
        BodyStudentUpdateVerify bodyStudentUpdateVerify = new BodyStudentUpdateVerify("1");
        mService.StudentUpdateVerify(user.getId(),bodyStudentUpdateVerify)
                .enqueue(new Callback<ResponeStudentUpdate>() {
                    @Override
                    public void onResponse(Call<ResponeStudentUpdate> call, Response<ResponeStudentUpdate> response) {
                        if (response.isSuccessful()) {
                            Student student = new Student();
                            student.setId(user.getId());
                            student.setTendangnhap(edt_username.getText().toString().trim());
                            student.setEmail(user.getEmail());
                            student.setVerify("1");
                            Common.mStudent = student;
                            startEditDetailActivity();
                        }
                        else {
                            try {
                                Log.e("Lỗi update verify", response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponeStudentUpdate> call, Throwable t) {
                        Log.e("Lỗi update verify", t.getMessage());
                    }
                });
    }


    public void startEditDetailActivity() {
        Intent intent = new Intent(SigninActivity.this, EditDetailStudentActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("activity","SigninActivity");
        startActivity(intent);
        finish();
    }

}