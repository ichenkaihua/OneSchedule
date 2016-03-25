package com.chenkaihua.oneschedule;

import android.app.Application;

import com.jiongbull.jlog.JLog;

/**
 * Created by chenkh on 16-3-19.
 */
public class App  extends Application{


    @Override
    public void onCreate() {
        super.onCreate();
        //初始化log
        JLog.init(this)
                .writeToFile(true)
                .setDebug(Config.IS_DEBUG);



    }
}
