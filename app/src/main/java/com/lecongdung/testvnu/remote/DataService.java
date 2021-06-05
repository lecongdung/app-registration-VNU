package com.lecongdung.testvnu.remote;

import com.lecongdung.testvnu.model.Cathi;
import com.lecongdung.testvnu.model.Kythi;
import com.lecongdung.testvnu.model.Lephi;
import com.lecongdung.testvnu.model.Monthi;
import com.lecongdung.testvnu.model.Student;
import com.lecongdung.testvnu.remote.entity.BodyLePhiUpdate;
import com.lecongdung.testvnu.remote.entity.BodyLogin;
import com.lecongdung.testvnu.remote.entity.BodyRegister;
import com.lecongdung.testvnu.remote.entity.BodySendOTP;
import com.lecongdung.testvnu.remote.entity.BodyStudentDelete;
import com.lecongdung.testvnu.remote.entity.BodyStudentUpdateDetail;
import com.lecongdung.testvnu.remote.entity.BodyStudentUpdateEmail;
import com.lecongdung.testvnu.remote.entity.BodyStudentUpdatePassword;
import com.lecongdung.testvnu.remote.entity.BodyStudentUpdateVerify;
import com.lecongdung.testvnu.remote.entity.ResponeRegister;
import com.lecongdung.testvnu.remote.entity.ResponeSendOTP;
import com.lecongdung.testvnu.remote.entity.ResponeStudentUpdate;
import com.lecongdung.testvnu.remote.entity.ResponeStudentUpdateDetail;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface DataService {
    @Headers({
            "Content-Type:application/json",
    })
    @POST("auth/login")
    Call<Student> Login (@Body BodyLogin body);

    @POST("auth/register")
    Call<ResponeRegister> Register (@Body BodyRegister body);

    @POST("auth/sendOTP")
    Call<ResponeSendOTP> SendOTP (@Body BodySendOTP body);

    @GET("student-acc/{id}")
    Call<Student> DetailStudent (@Part int id);

    @GET("student-acc/")
    Call<List<Student>> ListStudent();

    @PATCH("student-acc/{id}")
    Call<ResponeStudentUpdate> StudentUpdatePassword (@Path("id") int id, @Body BodyStudentUpdatePassword body);

    @PATCH("student-acc/{id}")
    Call<ResponeStudentUpdate> StudentUpdateEmail (@Path("id")int id, @Body BodyStudentUpdateEmail body);

    @PATCH("student-acc/{id}")
    Call<ResponeStudentUpdate> StudentUpdateVerify (@Path("id")int id, @Body BodyStudentUpdateVerify body);

    @PATCH("student-acc/{id}")
    Call<ResponeStudentUpdate> StudentUpdate (@Path("id") int id, @Body Student body);

    @HTTP(method = "DELETE", path = "student-acc/delete", hasBody = true)
    Call<Integer> StudentDelete (@Body BodyStudentDelete body);


    @PATCH("student-info/{id}")
    Call<ResponeStudentUpdateDetail> StudentUpdateDetail (@Path("id") int id, @Body BodyStudentUpdateDetail body);

    @GET("student-info/{id}")
    Call<ResponeStudentUpdateDetail> StudentDetail (@Path("id") int id);

    // Kỳ thi

    @GET("kythi")
    Call<List<Kythi>> GetAllKyThi ();

    @GET("mon-cathi")
    Call<List<Monthi>> GetAllMonThi ();

    @GET("kythi-cathi")
    Call<List<Cathi>> GetAllCaThi ();

    @GET("dangkythi-lephi/{id}")
    Call<Lephi> GetDangKyLePhi (@Path("id")int id);

    @PATCH("dangkythi-lephi/{id}")
    Call<Lephi> UpdateLePhi (@Path("id") int id, @Body BodyLePhiUpdate body);

}
