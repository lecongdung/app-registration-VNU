package com.lecongdung.testvnu.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lecongdung.testvnu.R;
import com.lecongdung.testvnu.model.Kythi;
import com.lecongdung.testvnu.model.MyKyThi;
import com.lecongdung.testvnu.view.KythiActivity;
import com.lecongdung.testvnu.view.MyKythiActivity;

import java.util.List;

public class MyKyThiAdapter extends RecyclerView.Adapter<MyKyThiAdapter.MyViewHolder> {

    private Context context;
    private List<MyKyThi> myKyThiList;

    public MyKyThiAdapter(Context context, List<MyKyThi> myKyThiList) {
        this.context = context;
        this.myKyThiList = myKyThiList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_my_test,null,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  MyKyThiAdapter.MyViewHolder holder, int position) {
        MyKyThi myKyThi = myKyThiList.get(position);
        holder.tv_makythi.setText(myKyThi.getMaKythi());
        holder.tv_tenkythi.setText(myKyThi.getTenKythi());
        if(myKyThi.getStatus().equals("0")) {
            holder.tv_trangthai.setText("Chưa thanh toán");
        }
        else {
            holder.tv_trangthai.setText("Đã thanh toán");
            holder.tv_trangthai.setTextColor(Color.rgb(0, 160, 66));
        }
        holder.layout.setOnClickListener(v -> {
            Intent intent = new Intent(context, MyKythiActivity.class);
            intent.putExtra("data",myKyThi);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return myKyThiList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_makythi, tv_tenkythi, tv_trangthai;
        LinearLayout layout;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_makythi = (TextView) itemView.findViewById(R.id.tv_makythi);
            tv_tenkythi = (TextView) itemView.findViewById(R.id.tv_tenkythi);
            tv_trangthai = (TextView) itemView.findViewById(R.id.tv_trangthai);
            layout = (LinearLayout) itemView.findViewById(R.id.layout_card_kythi);

        }
    }
}
