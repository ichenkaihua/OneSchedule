package com.chenkaihua.oneschedule.view.impl;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.afollestad.materialdialogs.folderselector.FileChooserDialog;
import com.chenkaihua.oneschedule.R;
import com.chenkaihua.oneschedule.base.BaseAppCompatActivity;
import com.chenkaihua.oneschedule.model.UserModel;
import com.chenkaihua.oneschedule.net.BaseResponse;
import com.chenkaihua.oneschedule.net.RetrofitBuilder;
import com.chenkaihua.oneschedule.net.api.FileApi;
import com.chenkaihua.oneschedule.presenter.ILoginPresenter;
import com.chenkaihua.oneschedule.presenter.impl.LoginPresenter;
import com.chenkaihua.oneschedule.view.ILoginView;
import com.jiongbull.jlog.JLog;
import com.jiongbull.jlog.util.FileUtils;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chenkh on 16-3-20.
 */
public class LoginActivity extends BaseAppCompatActivity implements ILoginView, FileChooserDialog.FileCallback {


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
    private List<File> mFileList = new ArrayList<>();

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
        startActivity(new Intent(this, RegisterActivity.class));


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
        showToast("登录成功:" + userModel.toString());


    }

    @Override
    public void onLoginFinal() {
        progressDialog.dismiss();


    }

    @Override
    public void onLoginFailue(int status, String msg) {
        showToast("登录失败!!" + msg);
    }


    @OnClick(R.id.btn_select_file)
    public void selectFile() {
        new FileChooserDialog.Builder(this)
                .chooseButton(R.string.md_choose_label)  // changes label of the choose button
                        // .initialPath("/sdcard/Download")  // changes initial path, defaults to external storage directory
             //   .mimeType("image/*") // Optional MIME type filter
                .tag("optional-identifier")
                .show();


    }


    @OnClick(R.id.btn_upload)
    public void onClickUpload() {

        uploadFile();


    }

    private void uploadFile() {
        MultipartBody body = parseToMultipartBody(mFileList);

        RetrofitBuilder.buildRetrofit().create(FileApi.class).testUplod(body)
        .enqueue(new Callback<BaseResponse<String>>() {
            @Override
            public void onResponse(Call<BaseResponse<String>> call, Response<BaseResponse<String>> response) {
                if (response.isSuccessful()) {
                    JLog.d("上传成功");
                    Toast.makeText(LoginActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                } else {
                    JLog.d("上传失败"+response.body().getMsg());
                    Toast.makeText(LoginActivity.this, "上传失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<String>> call, Throwable t) {

                Toast.makeText(LoginActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();


            }
        });






    }

    @Override
    public void onFileSelection(FileChooserDialog dialog, File file) {
        mFileList.add(file);
        JLog.d("选择文件:" + file.getAbsolutePath() + " size:" + file.length());
        Toast.makeText(LoginActivity.this, "选择了一个文件:size:" + file.getTotalSpace() + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
    }


    private MultipartBody parseToMultipartBody(List<File> files) {

        MultipartBody.Builder builder = new MultipartBody.Builder();


        int index = 0;
        for (File file : files) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
            JLog.d("file name:"+file.getName());
            builder.addFormDataPart("file", file.getName(), requestBody);
        }

        builder.setType(MultipartBody.FORM);
        MultipartBody multipartBody = builder.build();
        JLog.d(multipartBody.parts().toString());
        return multipartBody;
    }


}
