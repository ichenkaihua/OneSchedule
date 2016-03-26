package com.chenkaihua.oneschedule.view.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.chenkaihua.oneschedule.R;
import com.chenkaihua.oneschedule.base.BaseAppCompatActivity;
import com.chenkaihua.oneschedule.presenter.IRegisterPresenter;

import butterknife.ButterKnife;

/**
 * Created by ChenKH on 2016/3/26.
 */
public class RegisterActivity extends BaseAppCompatActivity implements IRegisterPresenter {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);






    }
}
