package com.chenkaihua.oneschedule.view.impl;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.chenkaihua.oneschedule.R;
import com.chenkaihua.oneschedule.base.BaseAppCompatActivity;
import com.chenkaihua.oneschedule.model.UserModel;
import com.chenkaihua.oneschedule.presenter.ILoginPresenter;
import com.chenkaihua.oneschedule.presenter.impl.LoginPresenter;
import com.chenkaihua.oneschedule.view.ILoginView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chenkh on 16-3-20.
 */
public class LoginActivity extends BaseAppCompatActivity implements ILoginView {


    @Bind(R.id.email)
    AutoCompleteTextView mEmail;
    @Bind(R.id.password)
    EditText mPassword;
    @Bind(R.id.btn_login)
    Button mEmailSignInButton;
    @Bind(R.id.email_login_form)
    LinearLayout mEmailLoginForm;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.btn_register)
    Button mBtnRegister;

    ProgressDialog progressDialog;

    private ILoginPresenter loginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_login);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter(this);
        mToolbar.setTitle("登录");
        loginPresenter.onCrate(savedInstanceState);
    }





    private void initView() {


    }


    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        loginPresenter.onDestory();
        super.onDestroy();

    }

    @OnClick(R.id.btn_login)
    public void onClick() {
        String name = mEmail.getText().toString();
        String password = mPassword.getText().toString();
        loginPresenter.login(name, password);
    }

    @OnClick(R.id.btn_register)
    public void onClickRegister() {
        startActivity(new Intent(this,RegisterActivity.class));



    }

    @Override
    public void onLoginStart() {
         progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("登录");
        progressDialog.setMessage("正在连接服务器");
        progressDialog.show();


    }

    @Override
    public void onLoginError(Throwable throwable) {
        showToast("登录失败，请检查网络是否连接成功");
        progressDialog.dismiss();

    }

    @Override
    public void onLoginSuccess(UserModel userModel) {
        showToast("登录成功:"+userModel.toString());


    }

    @Override
    public void onLoginFinal() {
        progressDialog.dismiss();


    }

    @Override
    public void onLoginFailue(int status,String msg) {
        showToast("登录失败!!"+msg);
    }
}
