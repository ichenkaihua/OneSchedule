package com.chenkaihua.oneschedule.net.api;

import com.chenkaihua.oneschedule.model.UserModel;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ChenKH on 2016/3/21.
 */
public interface UserApi {


    @GET("users")
    Observable<Response<UserModel>> login(@Query("phone") String phone, @Query("password") String password);


}
