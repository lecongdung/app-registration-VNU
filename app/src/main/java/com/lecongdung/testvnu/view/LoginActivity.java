package com.lecongdung.testvnu.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lecongdung.testvnu.R;
import com.lecongdung.testvnu.common.Common;
import com.lecongdung.testvnu.data.SessionManager;
import com.lecongdung.testvnu.model.Student;
import com.lecongdung.testvnu.remote.DataClient;
import com.lecongdung.testvnu.remote.DataService;
import com.lecongdung.testvnu.remote.entity.BodyLogin;
import com.lecongdung.testvnu.remote.entity.BodySendOTP;
import com.lecongdung.testvnu.remote.entity.BodyStudentUpdateEmail;
import com.lecongdung.testvnu.remote.entity.BodyStudentUpdatePassword;
import com.lecongdung.testvnu.remote.entity.ResponeSendOTP;
import com.lecongdung.testvnu.remote.entity.ResponeStudentUpdate;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText edt_username, edt_password;
    private Button btn_login;
    private TextView tv_forgot_password, btn_signup;
    private CardView card_view_loading;

    private DataService mService;

    private String mOTP = null;
    private Student mStudent = null;

    private SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sessionManager = new SessionManager(getApplication());

        initWeigth();
        initOnClick();
    }

    private void initWeigth() {
        edt_username = (EditText) findViewById(R.id.edt_username);
        edt_password = (EditText) findViewById(R.id.passwordEditText);
        btn_login = (Button) findViewById(R.id.btn_login);
        tv_forgot_password = (TextView) findViewById(R.id.tv_forgot_password);
        btn_signup = (TextView) findViewById(R.id.btn_signup);
        card_view_loading = (CardView) findViewById(R.id.card_view_loading);
        card_view_loading.setVisibility(View.GONE);

        mService = DataClient.getDataClient();

    }

    private void initOnClick() {

        btn_login.setOnClickListener(v -> {
            String username = edt_username.getText().toString().trim();
            String password = edt_password.getText().toString().trim();

            if (!username.equals("") && !password.equals("")) {
                card_view_loading.setVisibility(View.VISIBLE);
                BodyLogin bodyLogin = new BodyLogin(username, password);

                mService.Login(bodyLogin)
                        .enqueue(new Callback<Student>() {
                            @Override
                            public void onResponse(Call<Student> call, Response<Student> response) {
                                if (response.isSuccessful()) {
                                    card_view_loading.setVisibility(View.GONE);
                                    Common.mStudent = response.body();
                                    saveLogin();
                                    startHomeActivity();
                                }
                                else {
                                    Toast.makeText(LoginActivity.this, "Tên tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                                    card_view_loading.setVisibility(View.GONE);

                                }
                            }

                            @Override
                            public void onFailure(Call<Student> call, Throwable t) {
                                card_view_loading.setVisibility(View.GONE);
                                Toast.makeText(LoginActivity.this, "Lỗi đăng nhập", Toast.LENGTH_SHORT).show();
                                Log.e("Lỗi đăng nhập", t.getMessage());
                            }
                        });

            } else {
                Toast.makeText(this, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
                card_view_loading.setVisibility(View.GONE);
            }
        });

        tv_forgot_password.setOnClickListener(v -> {
            if (!edt_username.getText().toString().trim().equals("")) {
               findStudent(edt_username.getText().toString().trim());
            } else
                Toast.makeText(LoginActivity.this, "Không để trống tên đăng nhập", Toast.LENGTH_SHORT).show();
        });

        btn_signup.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SigninActivity.class);
            startActivity(intent);
        });
    }

    public void startHomeActivity() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    public void showDialogOTP(String email) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final View customLayout = getLayoutInflater().inflate(R.layout.dialog_input_otp, null);
        ImageView btn_resend = (ImageView) customLayout.findViewById(R.id.btn_resend);
        btn_resend.setOnClickListener(v -> {
            sendOTP(email);
            Toast.makeText(LoginActivity.this, "Đã gửi lại mã xác nhận", Toast.LENGTH_SHORT).show();
        });

        builder.setView(customLayout);
        builder.setTitle("Mã xác nhận");
        builder.setPositiveButton("Xác nhận", (dialog, which) -> {
            EditText edt_otp = (EditText) customLayout.findViewById(R.id.edt_otp);
            if (!edt_otp.getText().toString().trim().equals("")) {
                String otp = edt_otp.getText().toString().trim();
                if (otp.equals(mOTP)) {
                    dialog.dismiss();
                    showDialogNewPassword();
                } else {
                    Toast.makeText(LoginActivity.this,"Mã xác nhận không đúng",Toast.LENGTH_SHORT).show();
                }

            } else
                Toast.makeText(LoginActivity.this,"Không để trống mã xác nhận",Toast.LENGTH_SHORT).show();
        });
        builder.setNegativeButton("Hủy", (dialog, which) -> {
            dialog.dismiss();
        });

        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public void showDialogNewPassword() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View customLayout = getLayoutInflater().inflate(R.layout.dialog_input_newpassword, null);
        builder.setView(customLayout);
        builder.setTitle("Mật khẩu mới");
        builder.setPositiveButton("Xong", (dialog, which) -> {
            EditText edt_newpassword = (EditText) customLayout.findViewById(R.id.edt_newpassword);
            EditText edt_repassword = (EditText) customLayout.findViewById(R.id.edt_repassword);
            String newpassword = edt_newpassword.getText().toString();
            String repassword = edt_repassword.getText().toString();

            if (newpassword.length() < 6) {
                Toast.makeText(LoginActivity.this,"Độ dài mật khẩu lớn hơn 6 ký tự",Toast.LENGTH_SHORT).show();
            }
            else if(newpassword.equals("") || repassword.equals(""))
                Toast.makeText(LoginActivity.this,"Nhập đủ thông tin",Toast.LENGTH_SHORT).show();
            else {

                if (newpassword.equals(repassword)) {
                    changePassword(newpassword);
                    dialog.dismiss();
                } else
                    Toast.makeText(LoginActivity.this, "Nhập lại mật khẩu không chính xác", Toast.LENGTH_SHORT).show();

            }

        });
        builder.setNegativeButton("Hủy", (dialog, which) -> {
            dialog.dismiss();
        });

        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public void changePassword(String newpassword) {
        BodyStudentUpdatePassword bodyStudentUpdatePassword = new BodyStudentUpdatePassword(newpassword);
        mService.StudentUpdatePassword(mStudent.getId(),bodyStudentUpdatePassword)
                .enqueue(new Callback<ResponeStudentUpdate>() {
                    @Override
                    public void onResponse(Call<ResponeStudentUpdate> call, Response<ResponeStudentUpdate> response) {
                        Toast.makeText(LoginActivity.this,"Thay đổi mật khẩu thành công",Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onFailure(Call<ResponeStudentUpdate> call, Throwable t) {
                        Toast.makeText(LoginActivity.this,"Lỗi khi thay đổi mật khẩu",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void sendOTP(String email) {
        BodySendOTP bodySendOTP = new BodySendOTP(edt_username.getText().toString().trim(), email, false);
        mService.SendOTP(bodySendOTP)
                .enqueue(new Callback<ResponeSendOTP>() {
                    @Override
                    public void onResponse(Call<ResponeSendOTP> call, Response<ResponeSendOTP> response) {
                        mOTP = response.body().getResult();
                    }

                    @Override
                    public void onFailure(Call<ResponeSendOTP> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Không thể gửi mã xác nhận", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void findStudent(String username) {
        mService.ListStudent()
                .enqueue(new Callback<List<Student>>() {
                    @Override
                    public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                        List<Student> studentList = response.body();
                        for (Student student: studentList) {
                            if(student.getTendangnhap().equals(username)){
                                mStudent = student;
                            }
                        }

                        if (mStudent != null) {
                            sendOTP(mStudent.getEmail());
                            showDialogOTP(mStudent.getEmail());
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Student>> call, Throwable t) {
                        Log.e("ListStudent","Lỗi không nhận được danh sách Student");
                    }
                });

    }
    private void saveLogin() {
        sessionManager.SetLogin(true,Common.mStudent.getTendangnhap(),edt_password.getText().toString());
    }
}