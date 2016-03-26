package com.chenkaihua.oneschedule.presenter.impl;

import com.chenkaihua.oneschedule.model.UserModel;
import com.chenkaihua.oneschedule.net.BaseResponse;
import com.chenkaihua.oneschedule.net.RetrofitBuilder;
import com.chenkaihua.oneschedule.net.api.UserApi;
import com.chenkaihua.oneschedule.presenter.AbstractPresenter;
import com.chenkaihua.oneschedule.presenter.ILoginPresenter;
import com.chenkaihua.oneschedule.utils.StatusHelper;
import com.chenkaihua.oneschedule.view.ILoginView;
import com.jiongbull.jlog.JLog;

import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;

import static rx.android.schedulers.AndroidSchedulers.mainThread;
import static rx.schedulers.Schedulers.io;

/**
 * Created by chenkh on 16-3-20.
 */
public class LoginPresenter extends AbstractPresenter implements ILoginPresenter {


    private ILoginView loginView;

    private Subscriber<Response<BaseResponse<UserModel>>> loginscriber;


    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
    }


    @Override
    public void login(String phone, String password) {


        Observable<Response<BaseResponse<UserModel>>> responseObservable = RetrofitBuilder.buildRetrofit().create(UserApi.class).login(phone, password);
        responseObservable.
                subscribeOn(io()).observeOn(mainThread()).unsubscribeOn(io()).subscribe(
                new Subscriber<Response<BaseResponse<UserModel>>>() {

                    @Override
                    public void onStart() {
                        loginView.onLoginStart();

                    }

                    @Override
                    public void onCompleted() {
                        loginView.onLoginFinal();
                        unsubscribe();

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        JLog.e("error:" + e.getClass().getName() + e.getLocalizedMessage());
                        loginView.onLoginError(e);
                        loginView.onLoginFinal();
                        unsubscribe();
                    }

                    @Override

                    public void onNext(Response<BaseResponse<UserModel>> baseResponseResponse) {
                        int code = baseResponseResponse.code();
                        if (code == 200) {
                            loginView.onLoginSuccess(baseResponseResponse.body().getData());
                        } else if (StatusHelper.isCustomeStatus(code)) {
                            BaseResponse baseResponse = StatusHelper.parseErrorBody(baseResponseResponse);
                            loginView.onLoginFailue(code, baseResponse.getMsg());
                        } else
                            loginView.onLoginFailue(code, StatusHelper.getStatusMsg(baseResponseResponse));

                    }
                }
        );


    }

    @Override
    public void onDestory() {


    }
}
