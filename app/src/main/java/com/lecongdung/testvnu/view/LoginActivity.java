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
                                    Toast.makeText(LoginActivity.this, "T??n t??i kho???n ho???c m???t kh???u kh??ng ????ng", Toast.LENGTH_SHORT).show();
                                    card_view_loading.setVisibility(View.GONE);

                                }
                            }

                            @Override
                            public void onFailure(Call<Student> call, Throwable t) {
                                card_view_loading.setVisibility(View.GONE);
                                Toast.makeText(LoginActivity.this, "L???i ????ng nh???p", Toast.LENGTH_SHORT).show();
                                Log.e("L???i ????ng nh???p", t.getMessage());
                            }
                        });

            } else {
                Toast.makeText(this, "Vui l??ng ??i???n ????? th??ng tin", Toast.LENGTH_SHORT).show();
                card_view_loading.setVisibility(View.GONE);
            }
        });

        tv_forgot_password.setOnClickListener(v -> {
            if (!edt_username.getText().toString().trim().equals("")) {
               findStudent(edt_username.getText().toString().trim());
            } else
                Toast.makeText(LoginActivity.this, "Kh??ng ????? tr???ng t??n ????ng nh???p", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(LoginActivity.this, "???? g???i l???i m?? x??c nh???n", Toast.LENGTH_SHORT).show();
        });

        builder.setView(customLayout);
        builder.setTitle("M?? x??c nh???n");
        builder.setPositiveButton("X??c nh???n", (dialog, which) -> {
            EditText edt_otp = (EditText) customLayout.findViewById(R.id.edt_otp);
            if (!edt_otp.getText().toString().trim().equals("")) {
                String otp = edt_otp.getText().toString().trim();
                if (otp.equals(mOTP)) {
                    dialog.dismiss();
                    showDialogNewPassword();
                } else {
                    Toast.makeText(LoginActivity.this,"M?? x??c nh???n kh??ng ????ng",Toast.LENGTH_SHORT).show();
                }

            } else
                Toast.makeText(LoginActivity.this,"Kh??ng ????? tr???ng m?? x??c nh???n",Toast.LENGTH_SHORT).show();
        });
        builder.setNegativeButton("H???y", (dialog, which) -> {
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
        builder.setTitle("M???t kh???u m???i");
        builder.setPositiveButton("Xong", (dialog, which) -> {
            EditText edt_newpassword = (EditText) customLayout.findViewById(R.id.edt_newpassword);
            EditText edt_repassword = (EditText) customLayout.findViewById(R.id.edt_repassword);
            String newpassword = edt_newpassword.getText().toString();
            String repassword = edt_repassword.getText().toString();

            if (newpassword.length() < 6) {
                Toast.makeText(LoginActivity.this,"????? d??i m???t kh???u l???n h??n 6 k?? t???",Toast.LENGTH_SHORT).show();
            }
            else if(newpassword.equals("") || repassword.equals(""))
                Toast.makeText(LoginActivity.this,"Nh???p ????? th??ng tin",Toast.LENGTH_SHORT).show();
            else {

                if (newpassword.equals(repassword)) {
                    changePassword(newpassword);
                    dialog.dismiss();
                } else
                    Toast.makeText(LoginActivity.this, "Nh???p l???i m???t kh???u kh??ng ch??nh x??c", Toast.LENGTH_SHORT).show();

            }

        });
        builder.setNegativeButton("H???y", (dialog, which) -> {
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
                        Toast.makeText(LoginActivity.this,"Thay ?????i m???t kh???u th??nh c??ng",Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onFailure(Call<ResponeStudentUpdate> call, Throwable t) {
                        Toast.makeText(LoginActivity.this,"L???i khi thay ?????i m???t kh???u",Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(LoginActivity.this, "Kh??ng th??? g???i m?? x??c nh???n", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(LoginActivity.this, "T??i kho???n kh??ng t???n t???i", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Student>> call, Throwable t) {
                        Log.e("ListStudent","L???i kh??ng nh???n ???????c danh s??ch Student");
                    }
                });

    }
    private void saveLogin() {
        sessionManager.SetLogin(true,Common.mStudent.getTendangnhap(),edt_password.getText().toString());
    }
}