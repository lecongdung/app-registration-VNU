package com.lecongdung.testvnu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lecongdung.testvnu.R;
import com.lecongdung.testvnu.common.Common;
import com.lecongdung.testvnu.model.DetailsMonThi;
import com.lecongdung.testvnu.model.Monthi;

import java.util.List;

public class MonThiAdapter extends RecyclerView.Adapter<MonThiAdapter.MyViewHolder> {

    Context context;
    List<Monthi> detailsMonThiList;

    public MonThiAdapter(Context context, List<Monthi> detailsMonThiList) {
        this.context = context;
        this.detailsMonThiList = detailsMonThiList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_monthi, null, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Monthi monthi = detailsMonThiList.get(position);
        holder.tv_monthi_id.setText(monthi.getMaMonthi());
        String ngaythi = "";
        if(monthi.getNgaythi() != null) {
             ngaythi = Common.convertDateToString(monthi.getNgaythi(),Common.output);
        }
        holder.tv_ngaythi.setText(ngaythi);

        String giothi = "";
        if(monthi.getGiothi() != null) {
            giothi = Common.convertDateToString(monthi.getNgaythi(),Common.output4);
        }
        holder.tv_giothi.setText(giothi);
        holder.tv_diemthi.setText(monthi.getDiadiemthi());
        holder.tv_thoigianlambai.setText(monthi.getThoigianlambai()+"p");
        holder.tv_hinhthuc.setText(Common.convertHinhThuc(monthi.getLuachon()));
        holder.tv_lephi.setText(Common.covertFormNumber(monthi.getLephithi()) + "VND");
    }

    @Override
    public int getItemCount() {
        return detailsMonThiList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_monthi_id, tv_ngaythi, tv_thoigianlambai, tv_giothi, tv_diemthi, tv_hinhthuc, tv_lephi;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_monthi_id = (TextView) itemView.findViewById(R.id.tv_monthi_id);
            tv_ngaythi = (TextView) itemView.findViewById(R.id.tv_ngaythi);
            tv_giothi = (TextView) itemView.findViewById(R.id.tv_giothi);
            tv_diemthi = (TextView) itemView.findViewById(R.id.tv_diemthi);
            tv_thoigianlambai = (TextView) itemView.findViewById(R.id.tv_thoigianlambai);
            tv_hinhthuc = (TextView) itemView.findViewById(R.id.tv_hinhthuc);
            tv_lephi = (TextView) itemView.findViewById(R.id.tv_lephi);
        }
    }
}
