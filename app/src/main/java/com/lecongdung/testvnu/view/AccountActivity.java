package com.lecongdung.testvnu.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.RemoteMessage;
import com.lecongdung.testvnu.R;
import com.lecongdung.testvnu.common.Common;
import com.lecongdung.testvnu.data.SessionManager;
import com.lecongdung.testvnu.fcm.Notification;
import com.lecongdung.testvnu.model.Student;
import com.lecongdung.testvnu.remote.DataClient;
import com.lecongdung.testvnu.remote.DataService;
import com.lecongdung.testvnu.remote.entity.BodySendOTP;
import com.lecongdung.testvnu.remote.entity.BodyStudentUpdatePassword;
import com.lecongdung.testvnu.remote.entity.ResponeSendOTP;
import com.lecongdung.testvnu.remote.entity.ResponeStudentUpdate;
import com.lecongdung.testvnu.utils.BadgeView;
import com.lecongdung.testvnu.utils.BottomNavigationViewHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountActivity extends AppCompatActivity {
    private static final int ACTIVITY_NUMBER = 2;

    private BottomNavigationView bottomNavigationView;

    private TextView tv_username, tv_email, btn_signout;
    private Button btn_update_mail, btn_update_password, btn_update_detail, btn_view_detail;
    private ImageView btn_notification;
    private Context mContext = AccountActivity.this;

    private DataService mService;
    private String mOTP;

    private SessionManager sessionManager;

    private BadgeView badgeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        setupBottomNavigationView();
        mService = DataClient.getDataClient();
        sessionManager = new SessionManager(getApplication());

        initWeight();
        badgeView = new BadgeView(mContext, btn_notification);
        initOnClick();
    }

    private void initWeight() {
        tv_username = (TextView) findViewById(R.id.displayUsername);
        tv_email = (TextView) findViewById(R.id.email_textview);
        btn_signout = (TextView) findViewById(R.id.btn_signout);
        btn_notification = (ImageView) this.findViewById(R.id.btn_notification);

        btn_update_mail = (Button) findViewById(R.id.updateEmailButton);
        btn_update_password = (Button) findViewById(R.id.updatePasswordButton);
        btn_update_detail = (Button) findViewById(R.id.updateDetailsButton);
        btn_view_detail = (Button) findViewById(R.id.addPaymentInformationBtn);

        tv_username.setText(Common.mStudent.getTendangnhap());
        tv_email.setText(Common.mStudent.getEmail());
    }

    private void initOnClick() {
        btn_signout.setOnClickListener(v -> {
            startLoginActivity();
            sessionManager.SetLogin(false);
            Common.mStudent = null;
        });

        btn_update_mail.setOnClickListener(v -> {

        });

        btn_update_password.setOnClickListener(v -> {
            sendOTP(Common.mStudent.getEmail());
            showDialogOTP(Common.mStudent.getEmail());
        });

        btn_update_detail.setOnClickListener(v -> {
            Intent intent = new Intent(AccountActivity.this, EditDetailStudentActivity.class);
            intent.putExtra("activity", "AccountActivity");
            startActivity(intent);
        });

        btn_view_detail.setOnClickListener(v -> {
            Intent intent = new Intent(AccountActivity.this, ViewDetailActivity.class);
            startActivity(intent);
        });

        btn_notification.setOnClickListener(v -> {
            Intent intent = new Intent(this, NotificationActivity.class);
            startActivity(intent);
        });
    }

    public void sendOTP(String email) {
        BodySendOTP bodySendOTP = new BodySendOTP(Common.mStudent.getTendangnhap(), email, false);
        mService.SendOTP(bodySendOTP)
                .enqueue(new Callback<ResponeSendOTP>() {
                    @Override
                    public void onResponse(Call<ResponeSendOTP> call, Response<ResponeSendOTP> response) {
                        mOTP = response.body().getResult();
                    }

                    @Override
                    public void onFailure(Call<ResponeSendOTP> call, Throwable t) {
                        Toast.makeText(AccountActivity.this, "Không thể gửi mã xác nhận", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void showDialogOTP(String email) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final View customLayout = getLayoutInflater().inflate(R.layout.dialog_input_otp, null);
        ImageView btn_resend = (ImageView) customLayout.findViewById(R.id.btn_resend);
        btn_resend.setOnClickListener(v -> {
            sendOTP(email);
            Toast.makeText(AccountActivity.this, "Đã gửi lại mã xác nhận", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(AccountActivity.this, "Mã xác nhận không đúng", Toast.LENGTH_SHORT).show();
                }

            } else
                Toast.makeText(AccountActivity.this, "Không để trống mã xác nhận", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(AccountActivity.this, "Độ dài mật khẩu lớn hơn 6 ký tự", Toast.LENGTH_SHORT).show();
            } else if (newpassword.equals("") || repassword.equals(""))
                Toast.makeText(AccountActivity.this, "Nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            else {

                if (newpassword.equals(repassword)) {
                    changePassword(newpassword);
                    dialog.dismiss();
                } else
                    Toast.makeText(AccountActivity.this, "Nhập lại mật khẩu không chính xác", Toast.LENGTH_SHORT).show();

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
        mService.StudentUpdatePassword(Common.mStudent.getId(), bodyStudentUpdatePassword)
                .enqueue(new Callback<ResponeStudentUpdate>() {
                    @Override
                    public void onResponse(Call<ResponeStudentUpdate> call, Response<ResponeStudentUpdate> response) {
                        Toast.makeText(AccountActivity.this, "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponeStudentUpdate> call, Throwable t) {
                        Toast.makeText(AccountActivity.this, "Lỗi khi thay đổi mật khẩu", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void setupBottomNavigationView() {
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.enableNavigation(this, bottomNavigationView);

        //Change current highlighted icon
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUMBER);
        menuItem.setChecked(true);
    }

    private void startLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void startHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            startHomeActivity();
            return;
        }

        this.doubleBackToExitPressedOnce = true;

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    private void setupBadge(int reminderLength){
        if (reminderLength > 0){
            //Adds badge to the notification imageview on the toolbar
            badgeView.setTextSize(10);
            badgeView.setTextColor(Color.parseColor("#ffffff"));
            badgeView.setBadgeBackgroundColor(Color.parseColor("#ff0000"));
            badgeView.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
            badgeView.setText(String.valueOf(reminderLength));
            badgeView.setBadgeMargin(5, 0);
            badgeView.show();

        } else {
            badgeView.hide();

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(RemoteMessage notification){
        setupBadge(Common.number);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        setupBadge(Common.number);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}