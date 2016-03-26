package com.chenkaihua.oneschedule.presenter.impl;

import com.chenkaihua.oneschedule.model.UserModel;
import com.chenkaihua.oneschedule.net.BaseResponse;
import com.chenkaihua.oneschedule.net.RetrofitBuilder;
import com.chenkaihua.oneschedule.net.api.UserApi;
import com.chenkaihua.oneschedule.presenter.AbstractPresenter;
import com.chenkaihua.oneschedule.presenter.IRegisterPresenter;
import com.chenkaihua.oneschedule.utils.StatusHelper;
import com.chenkaihua.oneschedule.view.IRegisterView;

import retrofit2.Response;
import rx.Subscriber;

import static rx.android.schedulers.AndroidSchedulers.mainThread;
import static rx.schedulers.Schedulers.io;

/**
 * Created by chenkh on 16-3-26.
 */
public class RegisterPrensenter extends AbstractPresenter implements IRegisterPresenter {

    private IRegisterView mRegisterView;


    public RegisterPrensenter(IRegisterView registerView) {
        mRegisterView = registerView;
    }

    @Override
    public void register(String phone, String password) {

        UserModel userModel = new UserModel();
        userModel.setPhone(phone);
        userModel.setPassword(password);
        RetrofitBuilder.buildRetrofit().create(UserApi.class).register3(phone,password)
        .subscribeOn(io()).observeOn(mainThread()).unsubscribeOn(io())
                .subscribe(new Subscriber<Response<BaseResponse<UserModel>>>() {
                    @Override
                    public void onStart() {
                        mRegisterView.onRegisterStart();
                    }

                    @Override
                    public void onCompleted() {
                        mRegisterView.onRegisterFinal();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mRegisterView.onRegisterError(e);
                        mRegisterView.onRegisterFinal();

                    }



                    @Override
                    public void onNext(Response<BaseResponse<UserModel>> baseResponseResponse) {
                        int code = baseResponseResponse.code();
                        if (code == 200) {
                            mRegisterView.onRegisterSuccess(baseResponseResponse.body().getData());
                        } else if (StatusHelper.isCustomeStatus(code)) {
                            BaseResponse baseResponse = StatusHelper.parseErrorBody(baseResponseResponse);
                            mRegisterView.onRegisterFaile(code, baseResponse.getMsg());
                        } else
                            mRegisterView.onRegisterFaile(code, StatusHelper.getStatusMsg(baseResponseResponse));


                    }
                });


    }
}
