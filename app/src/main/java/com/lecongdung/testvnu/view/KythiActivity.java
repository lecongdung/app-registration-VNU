package com.lecongdung.testvnu.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.lecongdung.testvnu.R;
import com.lecongdung.testvnu.common.Common;
import com.lecongdung.testvnu.dialog.AlertDialog;
import com.lecongdung.testvnu.fragment.KythiFragment;
import com.lecongdung.testvnu.fragment.MonThiFragment;
import com.lecongdung.testvnu.model.Kythi;
import com.lecongdung.testvnu.model.Lephi;
import com.lecongdung.testvnu.remote.DataClient;
import com.lecongdung.testvnu.remote.DataService;
import com.lecongdung.testvnu.utils.NonSwipeableViewPager;
import com.lecongdung.testvnu.utils.SectionsStatePageAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KythiActivity extends AppCompatActivity
        implements KythiFragment.OnButtonClickListener, MonThiFragment.OnButtonClickListener  {

    private KythiFragment kythiFragment;
    private MonThiFragment monThiFragment;

    private SectionsStatePageAdapter pageAdapter;
    private NonSwipeableViewPager mViewPager;
    private RelativeLayout mRelativeLayout;

    private DataService mService;
    private Context mContext;
    private Kythi mKythi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kythi);

        mContext = this;
        mViewPager = (NonSwipeableViewPager) findViewById(R.id.container);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.removeableLayout);
        mService = DataClient.getDataClient();

        getData();

        setupFragments();

        setViewPager(0);
    }

    private void getData() {
        mKythi = (Kythi) getIntent().getSerializableExtra("data");
    }

    private void setupFragments() {
        kythiFragment = new KythiFragment();
        monThiFragment = new MonThiFragment(0,mKythi.getMaKythi());
        pageAdapter = new SectionsStatePageAdapter(getSupportFragmentManager());
        pageAdapter.addFragment(kythiFragment,"Fragment");
        pageAdapter.addFragment(monThiFragment,"Fragment");
    }

    private void setViewPager(int fragmentNumber) {
        mRelativeLayout.setVisibility(View.GONE);
        mViewPager.setAdapter(pageAdapter);
        mViewPager.setCurrentItem(fragmentNumber);
    }

    @Override
    public void onButtonClicked(View view) {
        int currPos = mViewPager.getCurrentItem();

        switch (view.getId()) {
            case R.id.btn_monthi_details:
                mViewPager.setCurrentItem(currPos+1);
                break;
            case R.id.btn_backarrow:
                mViewPager.setCurrentItem(currPos-1);
                break;
            case R.id.btn_thoat:
                finish();
                break;
            case R.id.btn_dangky:
                mService.GetDangKyLePhi(Common.mStudent.getId())
                        .enqueue(new Callback<Lephi>() {
                            @Override
                            public void onResponse(Call<Lephi> call, Response<Lephi> response) {
                                if (response.isSuccessful()) {
                                    Lephi mLephi = response.body();
                                    if(mLephi.getMakythi() != null && !mLephi.getMakythi().isEmpty()) {
                                        callDialog();
                                    }
                                    else {
                                        startDangKyActivity();
                                    }
                                }
                                else{
                                    startDangKyActivity();
                                }
                            }

                            @Override
                            public void onFailure(Call<Lephi> call, Throwable t) {
                                Toast.makeText(KythiActivity.this,"Không thể kết nối may chủ", Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
            default:
        }
    }

    private void callDialog() {
        AlertDialog dialog = new AlertDialog(this, mKythi);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void startDangKyActivity() {
        Intent intent = new Intent(this, DangKyThiActivity.class);
        intent.putExtra("data", mKythi);
        startActivity(intent);
    }
}