package com.lecongdung.testvnu.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lecongdung.testvnu.R;
import com.lecongdung.testvnu.model.Kythi;
import com.lecongdung.testvnu.view.HomeActivity;
import com.lecongdung.testvnu.view.KythiActivity;

import java.util.List;

public class KyThiAdapter extends RecyclerView.Adapter<KyThiAdapter.MyViewHolder> {

    private Context context;
    private List<Kythi> listMonthi;


    public KyThiAdapter(Context context, List<Kythi> listMonthi) {
        this.context = context;
        this.listMonthi = listMonthi;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_test,null,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KyThiAdapter.MyViewHolder holder, int position) {
        Kythi kythi = listMonthi.get(position);
        holder.tv_makythi.setText(kythi.getMaKythi());
        holder.tv_tenkythi.setText(kythi.getTenKythi());
        holder.layout.setOnClickListener(v -> {
            Intent intent = new Intent(context, KythiActivity.class);
            intent.putExtra("data",kythi);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return listMonthi.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_makythi, tv_tenkythi;
        LinearLayout layout;


        public MyViewHolder(View itemView) {
            super(itemView);
            tv_makythi = (TextView) itemView.findViewById(R.id.tv_makythi);
            tv_tenkythi = (TextView) itemView.findViewById(R.id.tv_tenkythi);
            layout = (LinearLayout) itemView.findViewById(R.id.layout_card_kythi);

        }
    }
}
