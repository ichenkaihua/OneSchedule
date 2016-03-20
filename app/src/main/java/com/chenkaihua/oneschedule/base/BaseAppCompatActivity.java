package com.chenkaihua.oneschedule.base;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by chenkh on 16-3-20.
 */
public class BaseAppCompatActivity extends AppCompatActivity implements IBaseView {


    @Override
    public void showToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSnackBar(View view,String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }


}
