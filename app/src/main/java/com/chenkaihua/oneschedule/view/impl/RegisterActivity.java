package com.chenkaihua.oneschedule.view.impl;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.chenkaihua.oneschedule.R;
import com.chenkaihua.oneschedule.base.BaseAppCompatActivity;
import com.chenkaihua.oneschedule.model.UserModel;
import com.chenkaihua.oneschedule.presenter.IRegisterPresenter;
import com.chenkaihua.oneschedule.presenter.impl.RegisterPrensenter;
import com.chenkaihua.oneschedule.view.IRegisterView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ChenKH on 2016/3/26.
 */
public class RegisterActivity extends BaseAppCompatActivity implements IRegisterView {


    @Bind(R.id.btn_login)
    Button mBtnLogin;
    @Bind(R.id.email_login_form)
    LinearLayout mEmailLoginForm;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.et_phone)
    EditText mEtPhone;
    @Bind(R.id.et_password)
    EditText mEtPassword;
    private IRegisterPresenter mRegisterPresenter;

    private ProgressDialog mRegisterProgressDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        mRegisterPresenter = new RegisterPrensenter(this);
    }


    @Override
    public void onRegisterStart() {
        mRegisterProgressDialog = new ProgressDialog(this);
        mRegisterProgressDialog.setTitle("注册");
        mRegisterProgressDialog.setMessage("正在注册....");
        mRegisterProgressDialog.show();
    }

    @Override
    public void onRegisterSuccess(UserModel userModel) {
        showToast("注册成功:" + userModel.toString());
    }

    @Override
    public void onRegisterFaile(int code, String msg) {
        showToast("注册失败!" + msg);
    }

    @Override
    public void onRegisterError(Throwable throwable) {
        showToast("网络开小差");
    }

    @Override
    public void onRegisterFinal() {
        if(mRegisterProgressDialog.isShowing()) mRegisterProgressDialog.dismiss();
        mRegisterProgressDialog = null;
    }

    @OnClick(R.id.btn_login)
    public void onClick() {
        String phone = mEtPhone.getText().toString();
        String password = mEtPassword.getText().toString();

        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
            showToast("用户名和密码不能为空");
            return;
        }

        mRegisterPresenter.register(phone, password);


    }
}
