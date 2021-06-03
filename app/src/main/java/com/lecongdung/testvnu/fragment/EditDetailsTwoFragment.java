package com.lecongdung.testvnu.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lecongdung.testvnu.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EditDetailsTwoFragment extends Fragment {
    public interface OnButtonClickListener {
        void onButtonClicked(View view);
    }

    public EditText edt_cmt, edt_dantoc, edt_ngaycap, edt_noicap, edt_khuvuc;
    private Button btn_done;
    private ImageView btn_backarrow;

    private OnButtonClickListener mOnButtonClickListener;
    private DatePickerDialog.OnDateSetListener date;
    private Calendar mCalandar;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View mView = inflater.inflate(R.layout.fragment_edit_detail_student_two, container, false);
        initWeight(mView);
        initClick();
        return mView;
    }

    private void initWeight(View view) {
        edt_cmt = (EditText) view.findViewById(R.id.edt_cmt);
        edt_dantoc = (EditText) view.findViewById(R.id.edt_dantoc);
        edt_ngaycap = (EditText) view.findViewById(R.id.edt_ngaycap);
        edt_noicap = (EditText) view.findViewById(R.id.edt_noicap);
        edt_khuvuc = (EditText) view.findViewById(R.id.edt_khuvuc);

        btn_done = (Button) view.findViewById(R.id.btn_done);
        btn_backarrow = (ImageView) view.findViewById(R.id.btn_backarrow);

        mCalandar = Calendar.getInstance();
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mCalandar.set(Calendar.YEAR, year);
                mCalandar.set(Calendar.MONTH, month);
                mCalandar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };
    }

    private void initClick() {
        btn_done.setOnClickListener(v -> {
            String cmt = edt_cmt.getText().toString().trim();
            String dantoc = edt_dantoc.getText().toString().trim();
            String ngaycap = edt_ngaycap.getText().toString().trim();
            String noicap = edt_noicap.getText().toString().trim();
            String khuvuc = edt_khuvuc.getText().toString().trim();

            if (cmt.isEmpty() || dantoc.isEmpty() || ngaycap.isEmpty() || noicap.isEmpty() || khuvuc.isEmpty()) {
                Toast.makeText(getContext(),"Vui lòng điền đủ thông tin",Toast.LENGTH_SHORT).show();
            }
            else
                mOnButtonClickListener.onButtonClicked(v);
        });

        btn_backarrow.setOnClickListener(v -> {
            mOnButtonClickListener.onButtonClicked(v);
        });


        edt_ngaycap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, 0);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), date, mCalandar
                        .get(Calendar.YEAR), mCalandar.get(Calendar.MONTH),
                        mCalandar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
                datePickerDialog.show();
            }
        });
    }

    private void updateLabel() {
        String dateFormat = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.UK);

        edt_ngaycap.setText(simpleDateFormat.format(mCalandar.getTime()));
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
