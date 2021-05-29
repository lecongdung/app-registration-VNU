package com.lecongdung.testvnu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;

import com.lecongdung.testvnu.R;
import com.lecongdung.testvnu.model.Monthi;

public class DetailTestDialog extends Dialog implements View.OnClickListener {

    private Context context;
    private Monthi monthi;

    public DetailTestDialog(@NonNull Context context, Monthi monthi) {
        super(context);
        this.monthi = monthi;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_detail_test);

        setupWidgets();

    }

    private void setupWidgets() {
    }


    @Override
    public void onClick(View v) {

    }
}
