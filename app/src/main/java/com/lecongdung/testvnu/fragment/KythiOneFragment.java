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
import com.lecongdung.testvnu.model.Kythi;
import com.lecongdung.testvnu.view.LoginActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class KythiOneFragment extends Fragment {
    public interface OnButtonClickListener {
        void onButtonClicked(View view);
    }

    private TextView tv_makythi, tv_tenkythi,tv_mota, tv_ngaythi, tv_socathi, tv_handk, tv_trangthai, btn_monthi_details, btn_thoat;
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
        btn_thoat = (TextView) mView.findViewById(R.id.btn_thoat);
        btn_dangky = (Button) mView.findViewById(R.id.btn_dangky);
    }

    private void initContent() throws ParseException {
        tv_makythi.setText(mKythi.getMaKythi());
        tv_mota.setText(mKythi.getMota());
        tv_tenkythi.setText(mKythi.getTenKythi());
        tv_socathi.setText(mKythi.getSocathi()+"");
        tv_trangthai.setText(mKythi.getTrangthai()+"");

        if(mKythi.getTungay() != null && mKythi.getToingay() != null && mKythi.getHandangky() != null)  {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat output2 = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
            Date d = sdf.parse(mKythi.getTungay());
            String tungngay = output.format(d);
            d = sdf.parse(mKythi.getToingay());
            String toingay = output.format(d);
            d = sdf.parse(mKythi.getHandangky());
            String handk = output2.format(d);

            tv_ngaythi.setText(tungngay + " - " + toingay);
            tv_handk.setText(handk);
        }
        else {
            tv_ngaythi.setText("");
            tv_handk.setText("");
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