package com.lecongdung.testvnu.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lecongdung.testvnu.R;
import com.lecongdung.testvnu.common.Common;
import com.lecongdung.testvnu.model.Kythi;
import com.lecongdung.testvnu.model.MyKyThi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyKyThiFragment extends Fragment {
    public interface OnButtonClickListener {
        void onButtonClicked(View view);
    }
    private TextView tv_makythi, tv_tenkythi,tv_mota, tv_ngaythi, tv_socathi, tv_handk, tv_trangthai, btn_monthi_details;
    private ImageView btn_thoat;
    private TextView tv_lephi, tv_lephi_danop,tv_lephi_ngaynop,tv_ngaydangky;
    private TextView btn_huydangky;

    private OnButtonClickListener mOnButtonClickListener;

    private MyKyThi mMyKyThi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View mView = inflater.inflate(R.layout.fragment_mykythi_details, container, false);
        getData();
        initWeight(mView);
        try {
            initContent();
        } catch (ParseException e) {
            e.printStackTrace();
            Log.e("loi date",e.getMessage());
        }
        initOnClick();


        return mView;
    }

    private void getData() {
        mMyKyThi = (MyKyThi) getActivity().getIntent().getSerializableExtra("data");
    }

    private void initWeight(View mView) {
        tv_makythi = (TextView) mView.findViewById(R.id.tv_makythi);
        tv_tenkythi = (TextView) mView.findViewById(R.id.tv_tenkythi);
        tv_mota = (TextView) mView.findViewById(R.id.tv_mota);
        tv_ngaythi = (TextView) mView.findViewById(R.id.tv_ngaythi);
        tv_socathi = (TextView) mView.findViewById(R.id.tv_socathi);
        tv_handk = (TextView) mView.findViewById(R.id.tv_handk);
        tv_trangthai = (TextView) mView.findViewById(R.id.tv_trangthai);
        tv_lephi = (TextView) mView.findViewById(R.id.tv_lephi);
        tv_lephi_danop = (TextView) mView.findViewById(R.id.tv_lephi_danop);
        tv_lephi_ngaynop = (TextView) mView.findViewById(R.id.tv_lephi_ngaynop);
        tv_ngaydangky = (TextView) mView.findViewById(R.id.tv_ngaydangky);

        btn_monthi_details = (TextView) mView.findViewById(R.id.btn_monthi_details);
        btn_thoat = (ImageView) mView.findViewById(R.id.btn_thoat);
        btn_huydangky = (TextView) mView.findViewById(R.id.btn_huydangky);
    }

    private void initContent() throws ParseException {
        tv_makythi.setText(mMyKyThi.getMaKythi());
        tv_mota.setText(mMyKyThi.getMota());
        tv_tenkythi.setText(mMyKyThi.getTenKythi());
        tv_socathi.setText(mMyKyThi.getSocathi()+"");
        tv_trangthai.setText(Common.convertTrangThai(mMyKyThi.getTrangthai()));

        String tungngay = "", toingay = "",handangky = "", ngaythu = "", ngaydangky="";

        if(mMyKyThi.getTungay() != null) {
            tungngay = Common.convertDateToString(mMyKyThi.getTungay(),Common.output);
        }
        if(mMyKyThi.getToingay() != null) {
            toingay = Common.convertDateToString(mMyKyThi.getToingay(),Common.output);
        }
        if(mMyKyThi.getHandangky() != null) {
            handangky = Common.convertDateToString(mMyKyThi.getHandangky(),Common.output2);
        }
        if(mMyKyThi.getNgaythu() != null) {
            ngaythu = Common.convertDateToString(mMyKyThi.getNgaythu(),Common.output2);
        }
        if(mMyKyThi.getNgaydangky() != null) {
            ngaydangky = Common.convertDateToString(mMyKyThi.getNgaydangky(),Common.output2);
        }

        if(!tungngay.isEmpty()&&!toingay.isEmpty()) tv_ngaythi.setText(tungngay + "-" + toingay);

        tv_handk.setText(handangky);
        tv_lephi.setText(mMyKyThi.getLephidangky() + " VNĐ");
        tv_lephi_danop.setText(mMyKyThi.getLephidanop() + " VNĐ");
        tv_lephi_ngaynop.setText(ngaythu);
        tv_ngaydangky.setText(ngaydangky);

        if(mMyKyThi.getTrangthai() == 2) {
            btn_huydangky.setVisibility(View.GONE);
        }
    }

    private void initOnClick() {
        btn_huydangky.setOnClickListener(v -> {
            mOnButtonClickListener.onButtonClicked(v);
        });

        btn_thoat.setOnClickListener(v -> {
            mOnButtonClickListener.onButtonClicked(v);
        });

        btn_monthi_details.setOnClickListener(v -> {
            mOnButtonClickListener.onButtonClicked(v);
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnButtonClickListener = (OnButtonClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(((Activity) context).getLocalClassName()
                    + " must implement OnButtonClickListener");
        }
    }

}
