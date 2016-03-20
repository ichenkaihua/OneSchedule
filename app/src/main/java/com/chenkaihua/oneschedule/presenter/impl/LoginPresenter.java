package com.chenkaihua.oneschedule.presenter.impl;

import com.chenkaihua.oneschedule.model.UserModel;
import com.chenkaihua.oneschedule.net.RetrofitBuilder;
import com.chenkaihua.oneschedule.net.api.UserApi;
import com.chenkaihua.oneschedule.presenter.ILoginPresenter;
import com.chenkaihua.oneschedule.view.ILoginView;
import com.jiongbull.jlog.JLog;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.Subject;

/**
 * Created by chenkh on 16-3-20.
 */
public class LoginPresenter implements ILoginPresenter {


    private ILoginView loginView;


    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
    }


    @Override
    public void login(String phone, String password) {
        RetrofitBuilder.buildRetrofit().create(UserApi.class).login(phone,password)
                .
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<UserModel>>() {
                    @Override
                    public void onCompleted() {
                        JLog.v("已完成");
                    }

                    @Override
                    public void onError(Throwable e) {
                        JLog.v("throwable:"+e.getMessage());
                    }

                    @Override
                    public void onNext(Response<UserModel> userModelResponse) {
                        JLog.v("是否成功:"+userModelResponse.code());
                        JLog.v("userModel:"+userModelResponse.body().toString());

                    }
                });
                /*.subscribe(new Observer<UserModel>() {
                    @Override
                    public void onCompleted() {
                            JLog.v("已完成");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        HttpException exception = (HttpException) e;
                        JLog.v("throwable:"+e.getMessage());

                    }

                    @Override
                    public void onNext(UserModel userModel) {
                        JLog.v("userModel:"+userModel.toString());
                    }
                });
*/
    }
}
