package com.lecongdung.testvnu.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.lecongdung.testvnu.R;
import com.lecongdung.testvnu.dialog.AlertDialog;
import com.lecongdung.testvnu.fragment.KythiFragment;
import com.lecongdung.testvnu.fragment.MonThiFragment;
import com.lecongdung.testvnu.model.Kythi;
import com.lecongdung.testvnu.remote.DataClient;
import com.lecongdung.testvnu.remote.DataService;
import com.lecongdung.testvnu.utils.NonSwipeableViewPager;
import com.lecongdung.testvnu.utils.SectionsStatePageAdapter;

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
        monThiFragment = new MonThiFragment(0,mKythi);
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
                callDialog();
                break;
            default:
        }
    }

    private void callDialog() {
        AlertDialog dialog = new AlertDialog(this, mKythi);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}