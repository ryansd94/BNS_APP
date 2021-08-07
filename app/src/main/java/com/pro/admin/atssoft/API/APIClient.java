package com.pro.admin.atssoft.API;

import com.pro.admin.atssoft.SessionManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static Retrofit retrofit = null;

   public static Retrofit getClient(String apiLink) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        //.baseUrl("https://reqres.in")
        //.baseUrl("http://192.168.1.20:8990")
        retrofit = new Retrofit.Builder()
                .baseUrl(apiLink)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();



        return retrofit;
    }
}
