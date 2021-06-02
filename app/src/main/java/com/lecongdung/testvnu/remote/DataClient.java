package com.lecongdung.testvnu.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataClient {
    public static String BASE_URI = "http://tripbricksapi.hanhchinhcong.net/v1/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseURL) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static DataService getDataClient() {
        return DataClient.getClient(BASE_URI).create(DataService.class);
    }
}
