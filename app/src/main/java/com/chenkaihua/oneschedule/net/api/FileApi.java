package com.chenkaihua.oneschedule.net.api;

import com.chenkaihua.oneschedule.net.BaseResponse;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * Created by chenkh on 16-3-31.
 */
public interface FileApi {


    @Multipart
    public void uploadFiles(@Part MultipartBody body);


    @Multipart()
    @POST("users/image")
    Call<BaseResponse<String>> testUplod(@Part("file") RequestBody body);


}
