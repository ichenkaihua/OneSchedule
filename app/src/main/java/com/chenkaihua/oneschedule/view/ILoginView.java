package com.chenkaihua.oneschedule.view;

import com.chenkaihua.oneschedule.base.IBaseView;
import com.chenkaihua.oneschedule.model.UserModel;

/**
 * Created by chenkh on 16-3-20.
 */
public interface ILoginView extends IBaseView {

    void onLoginStart();


    /**
     *  登录出错的时候调用，一般是网络问题和json解析出错问题
     * @param throwable exception
     */
    void onLoginError(Throwable throwable);


    /**
     * 当登录成功的时候，调用这个接口
     * @param userModel
     */
    void onLoginSuccess(UserModel userModel);

    /**
     * 最终结果，无论成功还是失败，都会调用这个接口
     */
    void onLoginFinal();


    /**
     * 当登录失败的时候回调这个接口，默认是因为
     * @param status
     * @param msg
     */
    void onLoginFailue(int status,String msg);



}
