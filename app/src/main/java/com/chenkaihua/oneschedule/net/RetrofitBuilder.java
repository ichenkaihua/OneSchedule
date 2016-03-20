package com.chenkaihua.oneschedule.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dellidc on 2016/3/16.
 */
public class RetrofitBuilder {

    public static Retrofit buildRetrofit() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging).retryOnConnectionFailure(true)
                .build();
        Retrofit retrofit = new Retrofit.Builder().client(client)
                .baseUrl(NetURL.BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit;
    }


}
