package com.lecongdung.testvnu.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lecongdung.testvnu.R;
import com.lecongdung.testvnu.common.Common;
import com.lecongdung.testvnu.model.Student;
import com.lecongdung.testvnu.remote.DataClient;
import com.lecongdung.testvnu.remote.DataService;
import com.lecongdung.testvnu.remote.entity.ResponeStudentUpdateDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewDetailActivity extends AppCompatActivity {

    TextView tv_tendangnhap,tv_email,tv_hoten,tv_phone,tv_ngaysinh,tv_gioitinh,tv_diachi,tv_dantoc,
            tv_cmt,tv_ngaycap,tv_noicap,tv_khuvuc;

    ImageView btn_back;

    DataService mService;

    Student mStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_detail);
        mService = DataClient.getDataClient();

        initWeight();
        findStudent(Common.mStudent.getTendangnhap());
        initOnclick();
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
        btn_back = (ImageView) findViewById(R.id.backArrowPickupRide);
    }
    private void initContent() {
        mService.StudentDetail(mStudent.getId())
                .enqueue(new Callback<ResponeStudentUpdateDetail>() {
                    @Override
                    public void onResponse(Call<ResponeStudentUpdateDetail> call, Response<ResponeStudentUpdateDetail> response) {
                        if(response.isSuccessful()) {
                            ResponeStudentUpdateDetail result = response.body();
                            tv_tendangnhap.setText(result.getTendangnhap());
                            tv_email.setText(result.getEmail());
                            tv_hoten.setText(result.getHoten());
                            tv_phone.setText(result.getSodienthoai());
                            tv_ngaysinh.setText(result.getNgaysinh());
                            if(result.equals("0")) {
                                tv_gioitinh.setText("Nam");
                            }else {
                                tv_gioitinh.setText("Nữ");
                            }
                            tv_diachi.setText(result.getNoisinh());
                            tv_dantoc.setText(result.getDantoc());
                            tv_cmt.setText(result.getSoCMND());
                            tv_ngaycap.setText(result.getNgaycap());
                            tv_noicap.setText(result.getNoicap());
                            tv_khuvuc.setText(result.getKhuvuc());
                        } else {
                            Toast.makeText(ViewDetailActivity.this,"Lỗi hiện thị thông tin",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponeStudentUpdateDetail> call, Throwable t) {

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
                            initContent();
                        }
                        else {
                            Toast.makeText(ViewDetailActivity.this, "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Student>> call, Throwable t) {
                        Log.e("ListStudent","Lỗi không nhận được danh sách Student");
                    }
                });

    }

    private void initOnclick() {
        btn_back.setOnClickListener(v -> {
            finish();
        });
    }
}