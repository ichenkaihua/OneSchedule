package com.chenkaihua.oneschedule.view.impl;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.chenkaihua.oneschedule.R;
import com.chenkaihua.oneschedule.base.BaseAppCompatActivity;
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
    @Bind(R.id.email_sign_in_button)
    Button mEmailSignInButton;
    @Bind(R.id.email_login_form)
    LinearLayout mEmailLoginForm;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    private ILoginPresenter loginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_login);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter(this);

    }


    private void initView() {


    }


    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }

    @OnClick(R.id.email_sign_in_button)
    public void onClick() {
        String name = mEmail.getText().toString();
        String password = mPassword.getText().toString();
        loginPresenter.login(name,password);
    }
}
