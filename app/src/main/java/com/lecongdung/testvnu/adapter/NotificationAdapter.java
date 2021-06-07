package com.lecongdung.testvnu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lecongdung.testvnu.R;
import com.lecongdung.testvnu.common.Common;
import com.lecongdung.testvnu.model.ThongBao;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder>{

    Context context;
    List<ThongBao> thongBaoList;

    public NotificationAdapter(Context context, List<ThongBao> thongBaoList) {
        this.context = context;
        this.thongBaoList = thongBaoList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_notification,null,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  NotificationAdapter.MyViewHolder holder, int position) {
        ThongBao thongBao = thongBaoList.get(position);
        holder.tv_title.setText(thongBao.getTitle());
        holder.tv_message.setText(thongBao.getMessage());
        holder.tv_date.setText(Common.convertDateToString(thongBao.getDate(),Common.output));
    }

    @Override
    public int getItemCount() {
        return thongBaoList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_title, tv_date, tv_message;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            tv_message = (TextView) itemView.findViewById(R.id.tv_message);
        }
    }
}
