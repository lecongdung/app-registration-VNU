package com.lecongdung.testvnu.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.lecongdung.testvnu.R;
import com.lecongdung.testvnu.model.Kythi;
import com.lecongdung.testvnu.remote.DataClient;
import com.lecongdung.testvnu.remote.DataService;

import java.text.ParseException;

public class KythiTwoFragment extends Fragment {
    public interface OnButtonClickListener {
        void onButtonClicked(View view);
    }

    private ImageView btn_backarrow;
    private RecyclerView mRecyclerView;

    private OnButtonClickListener mOnButtonClickListener;
    private DataService mService;

    private Kythi mKythi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View mView = inflater.inflate(R.layout.fragment_monthi_details, container, false);
        mService = DataClient.getDataClient();

        getData();
        initWeight(mView);

        initContent();
        initOnClick();


        return mView;
    }

    private void initWeight(View mView) {
        btn_backarrow = (ImageView) mView.findViewById(R.id.btn_backarrow);
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recycler_monthi);

    }
    private void initContent() {

    }
    private void initOnClick() {
        btn_backarrow.setOnClickListener(v -> {
            mOnButtonClickListener.onButtonClicked(v);
        });
    }
    private void getData() {
        mKythi = (Kythi) getActivity().getIntent().getSerializableExtra("data");
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
