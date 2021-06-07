package com.lecongdung.testvnu.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lecongdung.testvnu.R;
import com.lecongdung.testvnu.common.Common;
import com.lecongdung.testvnu.remote.DataClient;
import com.lecongdung.testvnu.remote.DataService;
import com.lecongdung.testvnu.remote.entity.BodyStudentUpdateDetail;
import com.lecongdung.testvnu.remote.entity.BodyStudentUpdateInfo;
import com.lecongdung.testvnu.remote.entity.ResponeStudentUpdateDetail;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditInfoStudentActivity extends AppCompatActivity {

    EditText tv_tinhtt,tv_huyentt,tv_nguoinhanthu,
            tv_diachinhanthu,tv_doituong,tv_truongl10,tv_tinhl10,tv_huyenl10,tv_k1l10,tv_k2l10,tv_kcnl10,
            tv_truongl11,tv_tinhl11,tv_huyenl11,tv_k1l11,tv_k2l11,tv_kcnl11,tv_truongl12,tv_tinhl12,
            tv_huyenl12,tv_k1l12,tv_k2l12,tv_kcnl12,tv_namtotnghiep,tv_dToan,tv_dVan,tv_dNgoaiNgu,tv_dLy,
            tv_dHoa,tv_dSinh,tv_dSu,tv_dDia,tv_dGDCD;

    TextView btn_done;
    ImageView btn_thoat;
    
    private DataService mService;
    private ResponeStudentUpdateDetail result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info_student);
        mService = DataClient.getDataClient();
        getData();
        initWeight();
        initContent();
        initOnClick();
    }


    private void getData() {
        result = (ResponeStudentUpdateDetail) getIntent().getSerializableExtra("data");
    }

    private void initWeight() {
        tv_tinhtt = (EditText) findViewById(R.id.tv_tinhtt);
        tv_huyentt = (EditText) findViewById(R.id.tv_huyentt);
        tv_nguoinhanthu = (EditText) findViewById(R.id.tv_nguoinhanthu);
        tv_diachinhanthu = (EditText) findViewById(R.id.tv_diachinhanthu);
        tv_doituong = (EditText) findViewById(R.id.tv_doituong);
        tv_truongl10 = (EditText) findViewById(R.id.tv_truongl10);
        tv_huyenl10 = (EditText) findViewById(R.id.tv_huyenl10);
        tv_tinhl10 = (EditText) findViewById(R.id.tv_tinhl10);
        tv_k1l10 = (EditText) findViewById(R.id.tv_k1l10);
        tv_k2l10 = (EditText) findViewById(R.id.tv_k2l10);
        tv_kcnl10 = (EditText) findViewById(R.id.tv_kcnl10);

        tv_truongl11 = (EditText) findViewById(R.id.tv_truongl11);
        tv_huyenl11 = (EditText) findViewById(R.id.tv_huyenl11);
        tv_tinhl11 = (EditText) findViewById(R.id.tv_tinhl11);
        tv_k1l11 = (EditText) findViewById(R.id.tv_k1l11);
        tv_k2l11 = (EditText) findViewById(R.id.tv_k2l11);
        tv_kcnl11 = (EditText) findViewById(R.id.tv_kcnl11);

        tv_truongl12 = (EditText) findViewById(R.id.tv_truongl12);
        tv_huyenl12 = (EditText) findViewById(R.id.tv_huyenl12);
        tv_tinhl12 = (EditText) findViewById(R.id.tv_tinhl12);
        tv_k1l12 = (EditText) findViewById(R.id.tv_k1l12);
        tv_k2l12 = (EditText) findViewById(R.id.tv_k2l12);
        tv_kcnl12 = (EditText) findViewById(R.id.tv_kcnl12);

        tv_namtotnghiep = (EditText) findViewById(R.id.tv_namtotnghiep);
        tv_dToan = (EditText) findViewById(R.id.tv_dToan);
        tv_dVan = (EditText) findViewById(R.id.tv_dVan);
        tv_dNgoaiNgu = (EditText) findViewById(R.id.tv_dNgoaingu);
        tv_dLy = (EditText) findViewById(R.id.tv_dLy);
        tv_dHoa = (EditText) findViewById(R.id.tv_dHoa);
        tv_dSinh = (EditText) findViewById(R.id.tv_dSinh);
        tv_dSu = (EditText) findViewById(R.id.tv_dSu);
        tv_dDia = (EditText) findViewById(R.id.tv_dDia);
        tv_dGDCD = (EditText) findViewById(R.id.tv_dGDCD);

        btn_done = (TextView) findViewById(R.id.btn_done);
        btn_thoat = (ImageView) findViewById(R.id.btn_thoat);
    }
    private void initContent() {
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

    private void initOnClick() {
        btn_thoat.setOnClickListener(v -> {
            finish();
        });

        btn_done.setOnClickListener(v -> {
            updateData();
            finish();
        });
    }

    private void updateData() {
        BodyStudentUpdateInfo body = new BodyStudentUpdateInfo();
        body.setHuyenTT(tv_huyentt.getText().toString().trim());
        body.setTinhTT(tv_tinhtt.getText().toString().trim());
        body.setNguoinhanthu(tv_nguoinhanthu.getText().toString().trim());
        body.setDiachinhanthu(tv_diachinhanthu.getText().toString().trim());
        body.setDoituonguutien(tv_doituong.getText().toString().trim());
        body.setTruonglop10(tv_truongl10.getText().toString().trim());
        body.setTruonglop11(tv_truongl11.getText().toString().trim());
        body.setTruonglop12(tv_truongl12.getText().toString().trim());
        body.setTinhlop10(tv_tinhl10.getText().toString().trim());
        body.setTinhlop11(tv_tinhl11.getText().toString().trim());
        body.setTinhlop12(tv_tinhl12.getText().toString().trim());
        body.setHuyenlop10(tv_huyenl10.getText().toString().trim());
        body.setHuyenlop11(tv_huyenl11.getText().toString().trim());
        body.setHuyenlop12(tv_huyenl12.getText().toString().trim());
        body.setL10K1(tv_k1l10.getText().toString().trim()+"");
        body.setL10K2(tv_k2l10.getText().toString().trim()+"");
        body.setL10CN(tv_kcnl10.getText().toString().trim()+"");
        body.setL11K1(tv_k1l11.getText().toString().trim()+"");
        body.setL11K2(tv_k2l11.getText().toString().trim()+"");
        body.setL11CN(tv_kcnl11.getText().toString().trim()+"");
        body.setL12K1(tv_k1l12.getText().toString().trim()+"");
        body.setL12K2(tv_k2l12.getText().toString().trim()+"");
        body.setL12CN(tv_kcnl12.getText().toString().trim()+"");
        body.setNamTotnghiep(tv_namtotnghiep.getText().toString().trim()+"");
        body.setdToan(tv_dToan.getText().toString().trim()+"");
        body.setdVan(tv_dVan.getText().toString().trim()+"");
        body.setdNgoaingu(tv_dNgoaiNgu.getText().toString().trim()+"");
        body.setDLy(tv_dLy.getText().toString().trim()+"");
        body.setdHoa(tv_dHoa.getText().toString().trim()+"");
        body.setdSinh(tv_dSinh.getText().toString().trim()+"");
        body.setdSu(tv_dSu.getText().toString().trim()+"");
        body.setdSinh(tv_dSinh.getText().toString().trim()+"");
        body.setdDia(tv_dDia.getText().toString().trim()+"");
        body.setdGDCD(tv_dGDCD.getText().toString().trim()+"");

        mService.StudentUpdateInfo(Common.mStudent.getId(),body)
                .enqueue(new Callback<ResponeStudentUpdateDetail>() {
                    @Override
                    public void onResponse(Call<ResponeStudentUpdateDetail> call, Response<ResponeStudentUpdateDetail> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(EditInfoStudentActivity.this,"Cập nhập hồ sơ thành công",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(EditInfoStudentActivity.this,"Cập nhập hồ sơ thất bại",Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponeStudentUpdateDetail> call, Throwable t) {
                        Toast.makeText(EditInfoStudentActivity.this,"Lỗi cập nhập hồ sơ ",Toast.LENGTH_SHORT).show();
                    }
                });

    }
}