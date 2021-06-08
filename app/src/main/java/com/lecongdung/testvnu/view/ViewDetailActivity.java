package com.lecongdung.testvnu.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
            tv_cmt,tv_ngaycap,tv_noicap,tv_khuvuc,tv_noisinh,tv_tinhtt,tv_huyentt,tv_nguoinhanthu,
            tv_diachinhanthu,tv_doituong,tv_truongl10,tv_tinhl10,tv_huyenl10,tv_k1l10,tv_k2l10,tv_kcnl10,
            tv_truongl11,tv_tinhl11,tv_huyenl11,tv_k1l11,tv_k2l11,tv_kcnl11,tv_truongl12,tv_tinhl12,
            tv_huyenl12,tv_k1l12,tv_k2l12,tv_kcnl12,tv_namtotnghiep,tv_dToan,tv_dVan,tv_dNgoaiNgu,tv_dLy,
            tv_dHoa,tv_dSinh,tv_dSu,tv_dDia,tv_dGDCD;

    ImageView btn_back,btn_edit_details;

    DataService mService;

    ResponeStudentUpdateDetail mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_detail);
        mService = DataClient.getDataClient();

        initWeight();
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
        tv_noisinh = (TextView) findViewById(R.id.tv_noisinh);
        tv_tinhtt = (TextView) findViewById(R.id.tv_tinhtt);
        tv_huyentt = (TextView) findViewById(R.id.tv_huyentt);
        tv_nguoinhanthu = (TextView) findViewById(R.id.tv_nguoinhanthu);
        tv_diachinhanthu = (TextView) findViewById(R.id.tv_diachinhanthu);
        tv_doituong = (TextView) findViewById(R.id.tv_doituong);
        tv_truongl10 = (TextView) findViewById(R.id.tv_truongl10);
        tv_huyenl10 = (TextView) findViewById(R.id.tv_huyenl10);
        tv_tinhl10 = (TextView) findViewById(R.id.tv_tinhl10);
        tv_k1l10 = (TextView) findViewById(R.id.tv_k1l10);
        tv_k2l10 = (TextView) findViewById(R.id.tv_k2l10);
        tv_kcnl10 = (TextView) findViewById(R.id.tv_kcnl10);

        tv_truongl11 = (TextView) findViewById(R.id.tv_truongl11);
        tv_huyenl11 = (TextView) findViewById(R.id.tv_huyenl11);
        tv_tinhl11 = (TextView) findViewById(R.id.tv_tinhl11);
        tv_k1l11 = (TextView) findViewById(R.id.tv_k1l11);
        tv_k2l11 = (TextView) findViewById(R.id.tv_k2l11);
        tv_kcnl11 = (TextView) findViewById(R.id.tv_kcnl11);

        tv_truongl12 = (TextView) findViewById(R.id.tv_truongl12);
        tv_huyenl12 = (TextView) findViewById(R.id.tv_huyenl12);
        tv_tinhl12 = (TextView) findViewById(R.id.tv_tinhl12);
        tv_k1l12 = (TextView) findViewById(R.id.tv_k1l12);
        tv_k2l12 = (TextView) findViewById(R.id.tv_k2l12);
        tv_kcnl12 = (TextView) findViewById(R.id.tv_kcnl12);

        tv_namtotnghiep = (TextView) findViewById(R.id.tv_namtotnghiep);
        tv_dToan = (TextView) findViewById(R.id.tv_dToan);
        tv_dVan = (TextView) findViewById(R.id.tv_dVan);
        tv_dNgoaiNgu = (TextView) findViewById(R.id.tv_dNgoaingu);
        tv_dLy = (TextView) findViewById(R.id.tv_dLy);
        tv_dHoa = (TextView) findViewById(R.id.tv_dHoa);
        tv_dSinh = (TextView) findViewById(R.id.tv_dSinh);
        tv_dSu = (TextView) findViewById(R.id.tv_dSu);
        tv_dDia = (TextView) findViewById(R.id.tv_dDia);
        tv_dGDCD = (TextView) findViewById(R.id.tv_dGDCD);


        btn_back = (ImageView) findViewById(R.id.backArrowPickupRide);
        btn_edit_details = (ImageView) findViewById(R.id.btn_edit_detail);
    }
    private void initContent() {
        mService.StudentDetail(Common.mStudent.getId())
                .enqueue(new Callback<ResponeStudentUpdateDetail>() {
                    @Override
                    public void onResponse(Call<ResponeStudentUpdateDetail> call, Response<ResponeStudentUpdateDetail> response) {
                        if(response.isSuccessful()) {
                            ResponeStudentUpdateDetail result = response.body();
                            mData = new ResponeStudentUpdateDetail();
                            mData.responeContent(result);
                            showContent(mData);
                        } else {
                            Toast.makeText(ViewDetailActivity.this,"Lỗi hiện thị thông tin",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponeStudentUpdateDetail> call, Throwable t) {

                    }
                });
    }


    private void initOnclick() {
        btn_back.setOnClickListener(v -> {
            finish();
        });
        btn_edit_details.setOnClickListener(v -> {
            Intent intent = new Intent(this,EditInfoStudentActivity.class);
            intent.putExtra("data",mData);
            startActivity(intent);
        });
    }

    private void showContent(ResponeStudentUpdateDetail result) {
        tv_tendangnhap.setText(result.getTendangnhap());
        tv_email.setText(result.getEmail());
        tv_hoten.setText(result.getHoten());
        tv_phone.setText(result.getSodienthoai());
        tv_ngaysinh.setText(Common.convertDateToString(result.getNgaysinh(),Common.output));

        String a[] = result.getNoisinh().split(", ");
        if(a.length >= 4) {
            tv_noisinh.setText(a[3]);
        }

        if(result.getGioitinh().equals("0")) {
            tv_gioitinh.setText("Nam");
        }else if(result.getGioitinh().equals("1")) {
            tv_gioitinh.setText("Nữ");
        }
        tv_diachi.setText(result.getNoisinh());
        tv_dantoc.setText(result.getDantoc());
        tv_cmt.setText(result.getSoCMND());
        tv_ngaycap.setText(Common.convertDateToString(result.getNgaycap(),Common.output));
        tv_noicap.setText(result.getNoicap());
        tv_khuvuc.setText(result.getKhuvuc());

        tv_doituong.setText(result.getDoituonguutien());

        tv_tinhtt.setText(result.getTinhTT());
        tv_huyentt.setText(result.getHuyenTT());
        tv_nguoinhanthu.setText(result.getNguoinhanthu());
        tv_diachinhanthu.setText(result.getDiachinhanthu());
        tv_truongl10.setText(result.getTruonglop10());
        tv_huyenl10.setText(result.getHuyenlop10());
        tv_tinhl10.setText(result.getTinhlop10());
        tv_k1l10.setText(result.getL10K1());
        tv_k2l10.setText(result.getL10K2());
        tv_kcnl10.setText(result.getL10CN());

        tv_truongl11.setText(result.getTruonglop11());
        tv_huyenl11.setText(result.getHuyenlop11());
        tv_tinhl11.setText(result.getTinhlop11());
        tv_k1l11.setText(result.getL11K1());
        tv_k2l11.setText(result.getL11K2());
        tv_kcnl11.setText(result.getL11CN());

        tv_truongl12.setText(result.getTruonglop12());
        tv_huyenl12.setText(result.getHuyenlop12());
        tv_tinhl12.setText(result.getTinhlop12());
        tv_k1l12.setText(result.getL12K1());
        tv_k2l12.setText(result.getL12K2());
        tv_kcnl12.setText(result.getL12CN());

        tv_namtotnghiep.setText(result.getNamTotnghiep());
        tv_dToan.setText(result.getdToan());
        tv_dVan.setText(result.getdVan());
        tv_dNgoaiNgu.setText(result.getdNgoaingu());
        tv_dLy.setText(result.getDLy());
        tv_dHoa.setText(result.getdHoa());
        tv_dSinh.setText(result.getdSinh());
        tv_dSu.setText(result.getdSu());
        tv_dDia.setText(result.getdDia());
        tv_dGDCD.setText(result.getdToan());
    }

    @Override
    protected void onStart() {
        super.onStart();
        initContent();
    }
}