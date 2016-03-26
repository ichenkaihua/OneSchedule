package com.chenkaihua.oneschedule.net.api;

import com.chenkaihua.oneschedule.model.UserModel;
import com.chenkaihua.oneschedule.net.BaseResponse;

import java.util.Map;

import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ChenKH on 2016/3/21.
 */
public interface UserApi {


    @GET("users/{phone}")
    Observable<Response<BaseResponse<UserModel>>> login(@Path("phone") String phone, @Query("password")
    String password);


    @POST("users")
    Observable<Response<BaseResponse<UserModel>>> register(@Body Map<String, Object> map);


    @POST("users")
    Observable<Response<BaseResponse<UserModel>>> register2(@Body UserModel userModel);


    @POST("users")
    Observable<Response<BaseResponse<UserModel>>> register3(@Query("phone") String phone,
                                                            @Query("password") String password);

}
