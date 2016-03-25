package com.chenkaihua.oneschedule.net.api;

import com.chenkaihua.oneschedule.model.UserModel;

import java.util.Map;

import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ChenKH on 2016/3/21.
 */
public interface UserApi {


    @GET("users")
    Observable<Response<UserModel>> login(@Query("phone") String phone, @Query("password")
    String password);


    @POST
    Observable<Response<UserModel>> register(@Body Map<String, Object> map);


}
