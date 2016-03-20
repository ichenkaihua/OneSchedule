package com.chenkaihua.oneschedule.presenter.impl;

import com.chenkaihua.oneschedule.presenter.ILoginPresenter;
import com.chenkaihua.oneschedule.view.ILoginView;

/**
 * Created by chenkh on 16-3-20.
 */
public class LoginPresenter implements ILoginPresenter {


    private ILoginView loginView;


    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
    }
}
