package com.lecongdung.testvnu.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.lecongdung.testvnu.R;
import com.lecongdung.testvnu.common.Common;
import com.lecongdung.testvnu.fragment.EditDetailsOneFragment;
import com.lecongdung.testvnu.fragment.EditDetailsTwoFragment;
import com.lecongdung.testvnu.remote.DataClient;
import com.lecongdung.testvnu.remote.DataService;
import com.lecongdung.testvnu.remote.entity.BodyStudentUpdateDetail;
import com.lecongdung.testvnu.remote.entity.ResponeStudentUpdateDetail;
import com.lecongdung.testvnu.utils.NonSwipeableViewPager;
import com.lecongdung.testvnu.utils.SectionsStatePageAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditDetailStudentActivity extends AppCompatActivity
        implements EditDetailsOneFragment.OnButtonClickListener, EditDetailsTwoFragment.OnButtonClickListener {

    private EditDetailsOneFragment editDetailsOneFragment;
    private EditDetailsTwoFragment editDetailsTwoFragment;

    private SectionsStatePageAdapter pageAdapter;
    private NonSwipeableViewPager mViewPager;
    private RelativeLayout mRelativeLayout;

    private DataService mService;
    private String flagPreActivity;


    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_detail);

        mContext = this;
        mViewPager = (NonSwipeableViewPager) findViewById(R.id.container);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.removeableLayout);
        mService = DataClient.getDataClient();

        getData();

        setupFragments();

        setViewPager(0);

        setContent();


    }

    private void setContent() {
        if(!flagPreActivity.equals("SigninActivity")) {

            mService.StudentDetail(Common.mStudent.getId())
                    .enqueue(new Callback<ResponeStudentUpdateDetail>() {
                        @Override
                        public void onResponse(Call<ResponeStudentUpdateDetail> call, Response<ResponeStudentUpdateDetail> response) {
                            if(response.isSuccessful()) {
                                ResponeStudentUpdateDetail result = new ResponeStudentUpdateDetail();
                                result.responeContent( response.body());

                                editDetailsOneFragment.edt_hoten.setText(result.getHoten());
                                editDetailsOneFragment.edt_phone.setText(result.getSodienthoai());
                                editDetailsOneFragment.edt_ngaysinh.setText(Common.convertDateToString(result.getNgaysinh(),Common.output3));

                                if(result.getGioitinh().equals("0")) {
                                    editDetailsOneFragment.maleRadioButton.setChecked(true);
                                }else if(result.getGioitinh().equals("1")){
                                    editDetailsOneFragment.femaleRadioButton.setChecked(true);
                                }
                                String diachi = result.getNoisinh();
                                String [] dc = diachi.split(", ");
                                if(dc.length >= 4) {
                                    editDetailsOneFragment.edt_duong.setText(dc[0]);
                                    editDetailsOneFragment.edt_phuong.setText(dc[1]);
                                    editDetailsOneFragment.edt_thanhpho.setText(dc[2]);
                                    editDetailsOneFragment.edt_tinh.setText(dc[3]);
                                }
                                editDetailsTwoFragment.edt_dantoc.setText(result.getDantoc());
                                editDetailsTwoFragment.edt_cmt.setText(result.getSoCMND());
                                editDetailsTwoFragment.edt_ngaycap.setText(Common.convertDateToString(result.getNgaycap(),Common.output3));
                                editDetailsTwoFragment.edt_noicap.setText(result.getNoicap());
                                editDetailsTwoFragment.edt_khuvuc.setText(result.getKhuvuc());
                            } else {
                                Toast.makeText(EditDetailStudentActivity.this,"L???i hi???n th??? th??ng tin",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponeStudentUpdateDetail> call, Throwable t) {

                        }
                    });
        }
    }

    private void getData() {
        flagPreActivity = getIntent().getStringExtra("activity");
    }


    private void setViewPager(int fragmentNumber) {
        mRelativeLayout.setVisibility(View.GONE);
        mViewPager.setAdapter(pageAdapter);
        mViewPager.setCurrentItem(fragmentNumber);
    }

    private void setupFragments() {
        editDetailsOneFragment = new EditDetailsOneFragment(flagPreActivity);
        editDetailsTwoFragment = new EditDetailsTwoFragment();
        pageAdapter = new SectionsStatePageAdapter(getSupportFragmentManager());
        pageAdapter.addFragment(editDetailsOneFragment,"Fragment");
        pageAdapter.addFragment(editDetailsTwoFragment,"Fragment");
    }


    @Override
    public void onButtonClicked(View view) {
        int currPos = mViewPager.getCurrentItem();

        switch (view.getId()) {
            case R.id.btn_next:
                mViewPager.setCurrentItem(currPos+1);
                break;
            case R.id.btn_backarrow:
                mViewPager.setCurrentItem(currPos-1);
                break;
            case R.id.btn_done:
                updateDetails();
                if(flagPreActivity.equals("SigninActivity")) {
                    startHomeActivity();
                }
                else {
                    finish();
                }
                break;
            default:
        }
    }

    private void updateDetails() {
        String hoten = editDetailsOneFragment.edt_hoten.getText().toString().trim();
        String ngaysinh = editDetailsOneFragment.edt_ngaysinh.getText().toString().trim();
        String phone = editDetailsOneFragment.edt_phone.getText().toString().trim();
        String duong = editDetailsOneFragment.edt_duong.getText().toString().trim();
        String phuong = editDetailsOneFragment.edt_phuong.getText().toString().trim();
        String thanhpho = editDetailsOneFragment.edt_thanhpho.getText().toString().trim();
        String tinh = editDetailsOneFragment.edt_tinh.getText().toString().trim();
        String gioitinh = editDetailsOneFragment.getGender();

        String cmt = editDetailsTwoFragment.edt_cmt.getText().toString().trim();
        String dantoc = editDetailsTwoFragment.edt_dantoc.getText().toString().trim();
        String ngaycap = editDetailsTwoFragment.edt_ngaycap.getText().toString().trim();
        String noicap = editDetailsTwoFragment.edt_noicap.getText().toString().trim();
        String khuvuc = editDetailsTwoFragment.edt_khuvuc.getText().toString().trim();

        BodyStudentUpdateDetail body = new BodyStudentUpdateDetail();
        body.setHoten(hoten);
        body.setSodienthoai(phone);
        body.setNgaysinh(ngaysinh);
        body.setGioitinh(gioitinh);
        body.setNoisinh(duong+", "+phuong+", "+thanhpho+", "+tinh);
        body.setDantoc(dantoc);
        body.setSoCMND(cmt);
        body.setNgaycap(ngaycap);
        body.setNoicap(noicap);
        body.setKhuvuc(khuvuc);

        mService.StudentUpdateDetail(Common.mStudent.getId(),body)
                .enqueue(new Callback<ResponeStudentUpdateDetail>() {
                    @Override
                    public void onResponse(Call<ResponeStudentUpdateDetail> call, Response<ResponeStudentUpdateDetail> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(EditDetailStudentActivity.this,"C???p nh???p th??ng tin th??nh c??ng",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(EditDetailStudentActivity.this,"L???i c???p nh???p th??ng tin",Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponeStudentUpdateDetail> call, Throwable t) {
                        Log.e("Loi updatedetai",t.getMessage());
                    }
                });

    }


    public void startHomeActivity() {
        Intent intent = new Intent(EditDetailStudentActivity.this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}