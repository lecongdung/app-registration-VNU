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

public class EditDetailsOneFragment extends Fragment {
    public interface OnButtonClickListener {
        void onButtonClicked(View view);
    }

    public EditText edt_hoten, edt_ngaysinh, edt_phone, edt_duong, edt_phuong, edt_thanhpho, edt_tinh;
    public RadioGroup mGenderGroup;
    public RadioButton maleRadioButton, femaleRadioButton;
    private Button btn_next;
    public ImageView btn_back;

    private OnButtonClickListener mOnButtonClickListener;
    private DatePickerDialog.OnDateSetListener date;
    private Calendar mCalandar;

    private String flagPreActivity;

    public EditDetailsOneFragment(String flagPreActivity) {
        this.flagPreActivity = flagPreActivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View mView = inflater.inflate(R.layout.fragment_edit_detail_student_one,container,false);

        initWeight(mView);
        initOnClick();


        return mView;
    }

    private void initWeight(View view) {
        edt_hoten = (EditText) view.findViewById(R.id.edt_hoten);
        edt_ngaysinh = (EditText) view.findViewById(R.id.edt_ngaysinh);
        edt_phone = (EditText) view.findViewById(R.id.edt_phone);
        edt_duong = (EditText) view.findViewById(R.id.edt_duong);
        edt_phuong = (EditText) view.findViewById(R.id.edt_phuong);
        edt_thanhpho = (EditText) view.findViewById(R.id.edt_thanhpho);
        edt_tinh = (EditText) view.findViewById(R.id.edt_tinh);
        mGenderGroup = (RadioGroup) view.findViewById(R.id.genderToggle);
        maleRadioButton = (RadioButton) view.findViewById(R.id.maleButton);
        femaleRadioButton = (RadioButton) view.findViewById(R.id.femaleButton);

        btn_back = (ImageView) view.findViewById(R.id.btn_back);
        if(flagPreActivity.equals("SigninActivity")) {
           btn_back.setVisibility(View.GONE);
        }
        btn_next = (Button) view.findViewById(R.id.btn_next);

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

    private void initOnClick() {
        btn_next.setOnClickListener(v -> {

            String hoten = edt_hoten.getText().toString().trim();
            String ngaysinh = edt_ngaysinh.getText().toString().trim();
            String phone = edt_phone.getText().toString().trim();
            String duong = edt_duong.getText().toString().trim();
            String phuong = edt_phuong.getText().toString().trim();
            String thanhpho = edt_thanhpho.getText().toString().trim();
            String tinh = edt_tinh.getText().toString().trim();



            if(hoten.isEmpty()||ngaysinh.isEmpty()||phone.isEmpty()||duong.isEmpty()||phuong.isEmpty()||thanhpho.isEmpty()||tinh.isEmpty()) {
                Toast.makeText(getContext(),"Vui l??ng ??i???n ????? th??ng tin",Toast.LENGTH_SHORT).show();
            }
            else if(!femaleRadioButton.isChecked() && !maleRadioButton.isChecked())
                Toast.makeText(getContext(),"Vui l??ng ch???n gi???i t??nh",Toast.LENGTH_SHORT).show();
            else
                    mOnButtonClickListener.onButtonClicked(v);
        });

        edt_ngaysinh.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, 0);

            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), date, mCalandar
                    .get(Calendar.YEAR), mCalandar.get(Calendar.MONTH),
                    mCalandar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
            datePickerDialog.show();
        });
        btn_back.setOnClickListener(v -> {
            getActivity().finish();
        });
    }

    public String getGender() {
        int whichIndex = mGenderGroup.getCheckedRadioButtonId();
        if (whichIndex == R.id.femaleButton) {
            return "1";
        } else if (whichIndex == R.id.maleButton) {
            return "0";
        }
        return "Kh??ng x??c ?????nh";
    }
    private void updateLabel() {
        String dateFormat = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.UK);

        edt_ngaysinh.setText(simpleDateFormat.format(mCalandar.getTime()));
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
