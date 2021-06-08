package com.lecongdung.testvnu.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
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
    private SearchView search_kythi;

    private OnButtonClickListener mOnButtonClickListener;
    private DataService mService;

    private int flag;
    private String mMakythi;
    private List<Monthi> detailsMonThiList = new ArrayList<>();

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
        initOnClick();


        return mView;
    }

    private void initWeight(View mView) {
        btn_backarrow = (ImageView) mView.findViewById(R.id.btn_backarrow);
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recycler_monthi);
        search_kythi = (SearchView) mView.findViewById(R.id.search_kythi);
    }
    private void initContent() {
        mService.GetAllMonThi()
                .enqueue(new Callback<List<Monthi>>() {
                    @Override
                    public void onResponse(Call<List<Monthi>> call, Response<List<Monthi>> response) {
                        if(response.isSuccessful()) {
                            for (Monthi monthi : response.body()) {
                                if(monthi.getMakythi().equals(mMakythi)){
                                    detailsMonThiList.add(monthi);
                                }
                            }
                            initList(detailsMonThiList);
                        }
                        else {
                            Toast.makeText(getContext(),"Lấy thông tin môn thi thất bại",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Monthi>> call, Throwable t) {
                        Toast.makeText(getContext(),"Lỗi lấy thông tin môn thi",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initList(List<Monthi> listKythi) {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myAdapter = new MonThiAdapter(getContext(),listKythi);
        mRecyclerView.setAdapter(myAdapter);
    }



    private void initOnClick() {
        btn_backarrow.setOnClickListener(v -> {
            mOnButtonClickListener.onButtonClicked(v);
        });

        search_kythi.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(detailsMonThiList != null) {
                    if (!detailsMonThiList.isEmpty())
                    findKythi(newText);
                }
                return false;
            }
        });
    }

    private void findKythi(String query) {
        ArrayList<Monthi> listResult = new ArrayList();
        if(query.isEmpty()) {
            listResult.addAll(detailsMonThiList);
        }
        else {
            for (Monthi monThi : detailsMonThiList) {
                if(monThi.getMaMonthi() != null && !monThi.getMaMonthi().isEmpty())  {
                    if(monThi.getMaMonthi().contains(query)) {
                        listResult.add(monThi);
                    }
                }
            }
        }
        myAdapter = new MonThiAdapter(getContext(), listResult);
        mRecyclerView.swapAdapter(myAdapter,false);
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
