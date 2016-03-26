package com.chenkaihua.oneschedule.view;

import com.chenkaihua.oneschedule.base.IBaseView;
import com.chenkaihua.oneschedule.model.UserModel;

/**
 * Created by chenkh on 16-3-20.
 */
public interface ILoginView extends IBaseView {

    void onLoginStart();


    void onLoginError(Throwable throwable);


    void onLoginSuccess(UserModel userModel);

    void onLoginCompeted();


    void onLoginFailue(int status);



}
