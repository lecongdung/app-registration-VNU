package com.lecongdung.testvnu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.lecongdung.testvnu.R;
import com.lecongdung.testvnu.model.Monthi;

public class DetailTestDialog extends Dialog implements View.OnClickListener {

    private Context context;
    private Monthi monthi;
    private boolean is_detail_test;

    private TextView tv_test_id, tv_test_name, tv_test_kythi, tv_test_makythi, tv_test_ngaythi,
            tv_test_cathi, tv_test_giothi, tv_test_diemthi, tv_test_hinhthuc, tv_test_thoigian,
            tv_test_lephi, tv_test_handk, tv_test_trangthai;

    private Button btn_dangky;
    private TextView btn_huydangky, btn_thoat;

    public DetailTestDialog(@NonNull Context context, Monthi monthi, boolean is_detail_test) {
        super(context);
        this.context = context;
        this.monthi = monthi;
        this.is_detail_test = is_detail_test;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_detail_test);

        setupWidgets();
        initContent();

    }


    private void setupWidgets() {
        tv_test_id = (TextView) findViewById(R.id.tv_test_id);
        tv_test_name = (TextView) findViewById(R.id.tv_test_name);
        tv_test_kythi = (TextView) findViewById(R.id.tv_test_kythi);
        tv_test_makythi = (TextView) findViewById(R.id.tv_test_makythi);
        tv_test_ngaythi = (TextView) findViewById(R.id.tv_test_ngaythi);
        tv_test_cathi = (TextView) findViewById(R.id.tv_test_cathi);
        tv_test_giothi = (TextView) findViewById(R.id.tv_test_giothi);
        tv_test_diemthi = (TextView) findViewById(R.id.tv_test_diemthi);
        tv_test_hinhthuc = (TextView) findViewById(R.id.tv_test_hinhthuc);
        tv_test_thoigian = (TextView) findViewById(R.id.tv_test_thoigian);
        tv_test_lephi = (TextView) findViewById(R.id.tv_test_lephi);
        tv_test_handk = (TextView) findViewById(R.id.tv_test_handk);
        tv_test_trangthai = (TextView) findViewById(R.id.tv_test_trangthai);

        btn_dangky = (Button) findViewById(R.id.btn_dangky);
        btn_huydangky = (TextView) findViewById(R.id.btn_huydangky);
        btn_thoat = (TextView) findViewById(R.id.btn_thoat);

        if (is_detail_test) {
            btn_dangky.setVisibility(View.VISIBLE);
            btn_huydangky.setVisibility(View.GONE);
        } else {
            btn_dangky.setVisibility(View.GONE);
            btn_huydangky.setVisibility(View.VISIBLE);
        }
    }

    private void initContent() {
        tv_test_id.setText(monthi.getMamonthi());
        tv_test_name.setText(monthi.getTenmonthi());
        tv_test_kythi.setText(monthi.getTenkythi());
        tv_test_makythi.setText(monthi.getMakythi());
        tv_test_ngaythi.setText(monthi.getNgaythi());
        tv_test_cathi.setText(monthi.getCathi());
        tv_test_giothi.setText(monthi.getGiothi());
        tv_test_diemthi.setText(monthi.getDiadiemthi());
        tv_test_thoigian.setText(monthi.getThoigianlambai() + "p");
        tv_test_lephi.setText(monthi.getLephithi());
        tv_test_handk.setText(monthi.getHandangky());
        tv_test_trangthai.setText(monthi.getLuachon());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dangky: {

            }
            case R.id.btn_huydangky: {

            }
            case R.id.btn_thoat: {
                dismiss();
                break;
            }
            default:
                break;
        }
        dismiss();
    }
}
