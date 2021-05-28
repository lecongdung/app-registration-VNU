package com.lecongdung.testvnu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lecongdung.testvnu.R;
import com.lecongdung.testvnu.model.Monthi;

import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.MyViewHolder> {

    private Context context;
    private OnItemTestListener onItemTestListener;
    private List<Monthi> listMonthi;

    public TestAdapter(Context context, OnItemTestListener onItemTestListener, List<Monthi> listMonthi) {
        this.context = context;
        this.onItemTestListener = onItemTestListener;
        this.listMonthi = listMonthi;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_test,null,false);
        return new MyViewHolder(view, onItemTestListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TestAdapter.MyViewHolder holder, int position) {
        Monthi monthi  = listMonthi.get(position);
        holder.tv_test_id.setText("Mã môn thi: " + monthi.getMamonthi());
        holder.tv_test_name.setText(monthi.getTenmonthi());
        holder.tv_test_semester.setText(monthi.getMakythi());
        holder.tv_test_id.setText(monthi.getNgaythi());

    }

    @Override
    public int getItemCount() {
        return listMonthi.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView tv_test_id, tv_test_name, tv_test_semester, tv_test_date;

        OnItemTestListener onItemTestListener;

        public MyViewHolder(View itemView, OnItemTestListener onItemTestListener) {
            super(itemView);
            tv_test_id = (TextView) itemView.findViewById(R.id.tv_test_id);
            tv_test_name = (TextView) itemView.findViewById(R.id.tv_test_name);
            tv_test_semester = (TextView) itemView.findViewById(R.id.tv_test_semester);
            tv_test_date = (TextView) itemView.findViewById(R.id.tv_test_date);

            this.onItemTestListener = onItemTestListener;
        }

        @Override
        public void onClick(View v) {
            onItemTestListener.OnItemTestClick(getAdapterPosition());
        }
    }
}
