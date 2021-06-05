package com.lecongdung.testvnu.fcm;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService  {

    @Headers({
            "Content-Type:application/json",
            "Authorization:key=AAAAx0b3oBg:APA91bEwD90_hAU8igTaAFh1gQfRjiYH1EdswhEzLuLRQQ0CfYAvrCzJq6j9-fv3Se4Su79KB1Mynb1u8mFnH8vkxiuUuCy4yCQrk2A6a9BOj9hI5ndWVfGoAdBqdnjTwVEZ_lTEuZDJ"
    })

    @POST("fcm/send")
    Call<Response> sendNotification(@Body Sender body);
}
