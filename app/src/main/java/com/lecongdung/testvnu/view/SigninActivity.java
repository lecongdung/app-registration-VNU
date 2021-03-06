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
import com.lecongdung.testvnu.data.SessionManager;
import com.lecongdung.testvnu.model.Student;
import com.lecongdung.testvnu.remote.DataClient;
import com.lecongdung.testvnu.remote.DataService;
import com.lecongdung.testvnu.remote.entity.BodyLogin;
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

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        sessionManager = new SessionManager(getApplication());


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
            Toast.makeText(SigninActivity.this, "Vui l??ng ??i???n ????? th??ng tin", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!Common.isValidEmail(email)) {
            Toast.makeText(SigninActivity.this, "Email sai ?????nh d???ng", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.length() < 6) {
            Toast.makeText(SigninActivity.this, "M???t kh???u ph???i l???n h??n 6 k?? t???", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!password.equals(repassword)) {
            Toast.makeText(SigninActivity.this, "Nh???p l???i m???t kh???u kh??ng ch??nh x??c", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(SigninActivity.this, "C?? l???i khi ????ng k??", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponeRegister> call, Throwable t) {
                        card_view_loading.setVisibility(View.GONE);
                        Toast.makeText(SigninActivity.this, "C?? l???i khi ????ng k??", Toast.LENGTH_SHORT).show();
                        Log.e("L???i ????ng nh???p", t.getMessage());
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
            Toast.makeText(SigninActivity.this, "???? g???i l???i m?? x??c nh???n", Toast.LENGTH_SHORT).show();

        });

        builder.setView(customLayout);
        builder.setTitle("X??c th???c t??i kho???n");
        builder.setPositiveButton("X??c nh???n", (dialog, which) -> {
            EditText edt_otp = (EditText) customLayout.findViewById(R.id.edt_otp);
            String otp = edt_otp.getText().toString().trim();
            if (!otp.isEmpty()) {
                if (otp.equals(responeRegister.getOTP())) {
                    verifyAccount(responeRegister.getUser());
                } else {
                    Toast.makeText(SigninActivity.this, "M?? x??c nh???n kh??ng ch??nh x??c", Toast.LENGTH_SHORT).show();
                    deleteAccount();
                    dialog.dismiss();
                }
            } else {
                Toast.makeText(SigninActivity.this, "Vui l??ng nh???p m?? x??c nh???n", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("H???y", (dialog, which) -> {
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
                                Log.e("L???i x??a t??i kho???n", response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        Log.e("L???i x??a t??i kho???n", t.getMessage());
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
                            loginAccount();
                        }
                        else {
                            try {
                                Log.e("L???i update verify", response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponeStudentUpdate> call, Throwable t) {
                        Log.e("L???i update verify", t.getMessage());
                    }
                });
    }

    private void loginAccount() {
        BodyLogin bodyLogin = new BodyLogin(edt_username.getText().toString().trim(),edt_password.getText().toString());
        mService.Login(bodyLogin)
                .enqueue(new Callback<Student>() {
                    @Override
                    public void onResponse(Call<Student> call, Response<Student> response) {
                        if (response.isSuccessful()) {
                            Common.mStudent = response.body();
                            saveLogin();
                            startEditDetailActivity();
                        }
                    }

                    @Override
                    public void onFailure(Call<Student> call, Throwable t) {

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

    private void saveLogin() {
        sessionManager.SetLogin(true,Common.mStudent.getTendangnhap(),edt_password.getText().toString());
    }

}