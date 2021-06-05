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
import java.util.List;

public class MonThiAdapter extends RecyclerView.Adapter<MonThiAdapter.MyViewHolder> {

    Context context;
    List<DetailsMonThi> detailsMonThiList;

    public MonThiAdapter(Context context, List<DetailsMonThi> detailsMonThiList) {
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
        DetailsMonThi monthi = detailsMonThiList.get(position);
        holder.tv_monthi_id.setText(monthi.getMaMonthi());

        String ngaythi = Common.convertDateToString(monthi.getNgaythi(),Common.output);
        holder.tv_ngaythi.setText(ngaythi);
        holder.tv_cathi.setText(monthi.getCathi()+"");
        holder.tv_giothi.setText(monthi.getGiothi());
        holder.tv_diemthi.setText(monthi.getDiemthi());
    }

    @Override
    public int getItemCount() {
        return detailsMonThiList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_monthi_id, tv_ngaythi, tv_cathi, tv_giothi, tv_diemthi;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_monthi_id = (TextView) itemView.findViewById(R.id.tv_monthi_id);
            tv_ngaythi = (TextView) itemView.findViewById(R.id.tv_ngaythi);
            tv_cathi = (TextView) itemView.findViewById(R.id.tv_cathi);
            tv_giothi = (TextView) itemView.findViewById(R.id.tv_giothi);
            tv_diemthi = (TextView) itemView.findViewById(R.id.tv_diemthi);
        }
    }
}
