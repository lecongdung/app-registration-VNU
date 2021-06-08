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
import com.lecongdung.testvnu.view.LoginActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class KythiFragment extends Fragment {
    public interface OnButtonClickListener {
        void onButtonClicked(View view);
    }

    private TextView tv_makythi, tv_tenkythi,tv_mota, tv_ngaythi, tv_socathi, tv_handk, tv_trangthai, btn_monthi_details;
    private ImageView btn_thoat;
    private Button btn_dangky;

    private OnButtonClickListener mOnButtonClickListener;

    private Kythi mKythi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View mView = inflater.inflate(R.layout.fragment_kythi_details, container, false);
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
        mKythi = (Kythi) getActivity().getIntent().getSerializableExtra("data");
    }


    private void initWeight(View mView) {
        tv_makythi = (TextView) mView.findViewById(R.id.tv_makythi);
        tv_tenkythi = (TextView) mView.findViewById(R.id.tv_tenkythi);
        tv_mota = (TextView) mView.findViewById(R.id.tv_mota);
        tv_ngaythi = (TextView) mView.findViewById(R.id.tv_ngaythi);
        tv_socathi = (TextView) mView.findViewById(R.id.tv_socathi);
        tv_handk = (TextView) mView.findViewById(R.id.tv_handk);
        tv_trangthai = (TextView) mView.findViewById(R.id.tv_trangthai);
        btn_monthi_details = (TextView) mView.findViewById(R.id.btn_monthi_details);
        btn_thoat = (ImageView) mView.findViewById(R.id.btn_thoat);
        btn_dangky = (Button) mView.findViewById(R.id.btn_dangky);

    }

    private void initContent() throws ParseException {
        tv_makythi.setText(mKythi.getMaKythi());
        tv_mota.setText(mKythi.getMota());
        tv_tenkythi.setText(mKythi.getTenKythi());
        tv_socathi.setText(mKythi.getSocathi()+"");
        tv_trangthai.setText(Common.convertTrangThai(mKythi.getTrangthai()));

        String tungngay = ""; String toingay = ""; String handangky = "";

        if(mKythi.getTungay() != null) {
            tungngay = Common.convertDateToString(mKythi.getTungay(),Common.output);
        }
        if(mKythi.getToingay() != null) {
            toingay = Common.convertDateToString(mKythi.getToingay(),Common.output);
        }
        if(mKythi.getHandangky() != null) {
            handangky = Common.convertDateToString(mKythi.getHandangky(),Common.output2);
        }

        if(!tungngay.isEmpty()&&!toingay.isEmpty()) tv_ngaythi.setText(tungngay + "-" + toingay);

        tv_handk.setText(handangky);

        if(mKythi.getTrangthai() != 0) {
            btn_dangky.setVisibility(View.GONE);
        }
    }

    private void initOnClick() {
        btn_dangky.setOnClickListener(v -> {
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
