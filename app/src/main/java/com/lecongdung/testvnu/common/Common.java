package com.lecongdung.testvnu.common;

import android.util.Log;

import com.lecongdung.testvnu.model.Student;
import com.lecongdung.testvnu.remote.DataClient;
import com.lecongdung.testvnu.remote.DataService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Common {
    public static Student mStudent;

    public static final String SDF = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String output = "dd/MM/yyyy";
    public static final String output3 = "yyyy-MM-dd";
    public static final String output2 = "HH:mm:ss dd/MM/yyyy";
    public static final String output4 = "HH:mm:ss";
    public static int number = 0;

    public static boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public static String convertDateToString(String raw,String form) {
        if(raw != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(Common.SDF);
            SimpleDateFormat output = new SimpleDateFormat(form);
            try {
                Date d = sdf.parse(raw);
                raw = output.format(d);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return raw;
    }

    public static String convertTrangThai(int status) {
        if(status == 0) return "Mở đăng ký";
        else if(status == 1) return "Hết hạn đăng ký";
        else return "Đã thi";
    }

    public static String convertHinhThuc(int status) {
        if(status == 1) return "Trắc nghiệm";
        else if(status == 2) return "Vấn đáp";
        else return "Tự luận";
    }

    public static String covertFormNumber(int number) {
        return  String.format("%,d", number);
    }

}
