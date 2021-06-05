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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lecongdung.testvnu.R;
import com.lecongdung.testvnu.adapter.KyThiAdapter;
import com.lecongdung.testvnu.adapter.MonThiAdapter;
import com.lecongdung.testvnu.model.Cathi;
import com.lecongdung.testvnu.model.DetailsMonThi;
import com.lecongdung.testvnu.model.Kythi;
import com.lecongdung.testvnu.model.Monthi;
import com.lecongdung.testvnu.model.MyKyThi;
import com.lecongdung.testvnu.remote.DataClient;
import com.lecongdung.testvnu.remote.DataService;
import com.lecongdung.testvnu.view.HomeActivity;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MonThiFragment extends Fragment {
    public interface OnButtonClickListener {
        void onButtonClicked(View view);
    }

    private ImageView btn_backarrow;
    private RecyclerView mRecyclerView;
    private MonThiAdapter myAdapter;

    private OnButtonClickListener mOnButtonClickListener;
    private DataService mService;

    private int flag;
    private String mMakythi;
    private List<DetailsMonThi> detailsMonThiList = new ArrayList<>();

    public MonThiFragment(int flag, String mMakythi) {
        this.flag = flag;
        this.mMakythi = mMakythi;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View mView = inflater.inflate(R.layout.fragment_monthi_details, container, false);
        mService = DataClient.getDataClient();

        initWeight(mView);

        initContent();
        initList(detailsMonThiList);
        initOnClick();


        return mView;
    }

    private void initWeight(View mView) {
        btn_backarrow = (ImageView) mView.findViewById(R.id.btn_backarrow);
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recycler_monthi);
    }
    private void initContent() {
        mService.GetAllMonThi()
                .enqueue(new Callback<List<Monthi>>() {
                    @Override
                    public void onResponse(Call<List<Monthi>> call, Response<List<Monthi>> response) {
                        if(response.isSuccessful()) {
                            List<Monthi> monthiList = response.body();
                            for (Monthi monthi : monthiList) {
                                if(monthi.getMakythi().equals(mMakythi)){
                                    DetailsMonThi detailsMonThi = new DetailsMonThi();
                                    detailsMonThi.setMaMonthi(monthi.getMamonthi());
                                    detailsMonThi.setCathi(monthi.getCathi());
                                    detailsMonThi.setDiemthi(monthi.getMadiemthi());

                                    getTimeAsyn(detailsMonThi);
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Monthi>> call, Throwable t) {

                    }
                });
    }

    private void initList(List<DetailsMonThi> listKythi) {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myAdapter = new MonThiAdapter(getContext(),listKythi);
        mRecyclerView.setAdapter(myAdapter);
    }

    private void getTimeAsyn(DetailsMonThi detailsMonThi) {
        mService.GetAllCaThi()
                .enqueue(new Callback<List<Cathi>>() {
                    @Override
                    public void onResponse(Call<List<Cathi>> call, Response<List<Cathi>> response) {
                        if(response.isSuccessful()) {
                            List<Cathi> cathiList = response.body();
                            for(Cathi cathi : cathiList) {
                                if (cathi.getMakythi().equals(mMakythi)
                                        && cathi.getCathi() == detailsMonThi.getCathi()) {
                                    detailsMonThi.setNgaythi(cathi.getNgaythi());
                                    detailsMonThi.setGiothi(cathi.getGiothi());
                                }
                            }
                        }
                        detailsMonThiList.add(detailsMonThi);
                        myAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<Cathi>> call, Throwable t) {

                    }
                });
    }
    private void getTimeSync(DetailsMonThi detailsMonThi) {
        try {
            Response<List<Cathi>> response = mService.GetAllCaThi().execute();
            if(response.isSuccessful()) {
                List<Cathi> cathiList = response.body();
                for(Cathi cathi : cathiList) {
                    if (cathi.getMakythi().equals(mMakythi)
                            && cathi.getCathi() == detailsMonThi.getCathi()) {
                        detailsMonThi.setNgaythi(cathi.getNgaythi());
                        detailsMonThi.setGiothi(cathi.getGiothi());
                    }
                }
            }
            detailsMonThiList.add(detailsMonThi);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void initOnClick() {
        btn_backarrow.setOnClickListener(v -> {
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
