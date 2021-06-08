package com.lecongdung.testvnu.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lecongdung.testvnu.R;
import com.lecongdung.testvnu.common.Common;
import com.lecongdung.testvnu.dialog.SuccessDialog;
import com.lecongdung.testvnu.model.Kythi;
import com.lecongdung.testvnu.model.Lephi;
import com.lecongdung.testvnu.model.Monthi;
import com.lecongdung.testvnu.model.MyKyThi;
import com.lecongdung.testvnu.remote.DataClient;
import com.lecongdung.testvnu.remote.DataService;
import com.lecongdung.testvnu.remote.entity.BodyLePhiUpdate;
import com.lecongdung.testvnu.remote.entity.ResponeStudentUpdateDetail;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangKyThiActivity extends AppCompatActivity {

    private TextView tv_tendangnhap, tv_email, tv_hoten, tv_phone, tv_ngaysinh, tv_gioitinh, tv_diachi, tv_dantoc,
            tv_cmt, tv_ngaycap, tv_noicap, tv_khuvuc,tv_lephi;
    private TextView btn_edit_details, btn_thoat;
    private Button btn_ghidanh;
    private RadioGroup radioGroup;
    private RadioButton rb_tructiep,rb_chuyenkhoan;

    private Kythi mKythi;
    private DataService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky_thi);
        mService = DataClient.getDataClient();
        getData();

        initWeight();
        initContent();
        initOnClick();
    }

    private void initWeight() {
        tv_tendangnhap = (TextView) findViewById(R.id.tv_tendangnhap);
        tv_email = (TextView) findViewById(R.id.tv_email);
        tv_hoten = (TextView) findViewById(R.id.tv_hoten);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        tv_ngaysinh = (TextView) findViewById(R.id.tv_ngaysinh);
        tv_gioitinh = (TextView) findViewById(R.id.tv_gioitinh);
        tv_diachi = (TextView) findViewById(R.id.tv_diachi);
        tv_dantoc = (TextView) findViewById(R.id.tv_dantoc);
        tv_cmt = (TextView) findViewById(R.id.tv_cmt);
        tv_ngaycap = (TextView) findViewById(R.id.tv_ngaycap);
        tv_noicap = (TextView) findViewById(R.id.tv_noicap);
        tv_khuvuc = (TextView) findViewById(R.id.tv_khuvuc);
        tv_lephi = (TextView) findViewById(R.id.tv_lephi);

        btn_edit_details = (TextView) findViewById(R.id.btn_edit_detail);
        btn_ghidanh = (Button) findViewById(R.id.btn_ghidanh);
        btn_thoat = (TextView) findViewById(R.id.btn_thoat);

        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        rb_tructiep = (RadioButton) findViewById(R.id.radio_a);
        rb_chuyenkhoan = (RadioButton) findViewById(R.id.radio_b);
    }

    private void initContent() {
        mService.StudentDetail(Common.mStudent.getId())
                .enqueue(new Callback<ResponeStudentUpdateDetail>() {
                    @Override
                    public void onResponse(Call<ResponeStudentUpdateDetail> call, Response<ResponeStudentUpdateDetail> response) {
                        if (response.isSuccessful()) {
                            ResponeStudentUpdateDetail result = response.body();
                            tv_tendangnhap.setText(result.getTendangnhap());
                            tv_email.setText(result.getEmail());
                            tv_hoten.setText(result.getHoten());
                            tv_phone.setText(result.getSodienthoai());
                            tv_ngaysinh.setText(result.getNgaysinh());
                            if (result.equals("0")) {
                                tv_gioitinh.setText("Nam");
                            } else {
                                tv_gioitinh.setText("Nữ");
                            }
                            tv_diachi.setText(result.getNoisinh());
                            tv_dantoc.setText(result.getDantoc());
                            tv_cmt.setText(result.getSoCMND());
                            tv_ngaycap.setText(result.getNgaycap());
                            tv_noicap.setText(result.getNoicap());
                            tv_khuvuc.setText(result.getKhuvuc());
                        } else {
                            Toast.makeText(DangKyThiActivity.this, "Lỗi hiện thị thông tin", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponeStudentUpdateDetail> call, Throwable t) {

                    }
                });

        mService.GetAllMonThi()
                .enqueue(new Callback<List<Monthi>>() {
                    @Override
                    public void onResponse(Call<List<Monthi>> call, Response<List<Monthi>> response) {
                        if(response.isSuccessful()) {
                            int sum = 0;
                            for (Monthi monthi : response.body()) {
                                if(monthi.getMakythi().equals(mKythi.getMaKythi())){
                                    sum += monthi.getLephithi();
                                }
                            }
                            tv_lephi.setText("Lệ phí: "+Common.covertFormNumber(sum)+ " VND");
                        }
                        else {
                            Toast.makeText(DangKyThiActivity.this,"Tính lệ phí thất bại",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Monthi>> call, Throwable t) {
                        Toast.makeText(DangKyThiActivity.this,"Lỗi lấy thông tin môn thi",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initOnClick() {
        btn_ghidanh.setOnClickListener(v -> {
            ghidanhKythi();
        });

        btn_edit_details.setOnClickListener(v -> {
            Intent intent = new Intent(DangKyThiActivity.this, EditDetailStudentActivity.class);
            intent.putExtra("activity", "DangKyThiActivity");
            startActivity(intent);
        });

        btn_thoat.setOnClickListener(v -> {
            finish();
        });
    }

    private void getData() {
        mKythi = (Kythi) getIntent().getSerializableExtra("data");
    }
    private void ghidanhKythi() {
        BodyLePhiUpdate body = new BodyLePhiUpdate();
        body.setMakythi(mKythi.getMaKythi());
        body.setLephidangky(150000);
        body.setLephidanop(0);

        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);

        body.setNgaydangky(strDate);
        body.setStatus("0");
        mService.UpdateLePhi(Common.mStudent.getId(),body)
                .enqueue(new Callback<Lephi>() {
                    @Override
                    public void onResponse(Call<Lephi> call, Response<Lephi> response) {
                        if (response.isSuccessful()) {
                            showDialog();
                        }
                        else
                            Toast.makeText(DangKyThiActivity.this,"Ghi danh không thành công",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Lephi> call, Throwable t) {
                        Log.e("loi dangkythi", t.getMessage());
                    }
                });
    }

    private void showDialog() {

        int flag = 0;

        int selectedId = radioGroup.getCheckedRadioButtonId();
        if(selectedId == R.id.radio_b) {
            flag = 1;
        }else flag = 0;

        SuccessDialog dialog = new SuccessDialog(this, this,flag);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}