package com.chenkaihua.oneschedule.view;

import com.chenkaihua.oneschedule.base.IBaseView;
import com.chenkaihua.oneschedule.model.UserModel;

/**
 * Created by ChenKH on 2016/3/26.
 */
public interface IRegisterView extends IBaseView {


    /**
     * 当注册开始的时候调用
     */
    void onRegisterStart();

    /**
     * 当注册成功时回调此方法
     * @param userModel
     */
    void onRegisterSuccess(UserModel userModel);


    /**
     * 当注册失败时回调这个方法
     * @param code http状态码
     * @param msg 提示信息
     */
    void onRegisterFaile(int code, String msg);


    /**
     * 当发生严重exception时回调这个方法，一般是网络连接出错或者json解析出错
     * @param throwable
     */
    void onRegisterError(Throwable throwable);


    /**
     * 注册接口调用后，无论成功与否都会调用这个接口
     */
    void onRegisterFinal();





}
