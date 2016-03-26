package com.chenkaihua.oneschedule.utils;

import com.chenkaihua.oneschedule.Config;
import com.chenkaihua.oneschedule.net.BaseResponse;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Response;

/**
 * Created by chenkh on 16-3-26.
 */
public class StatusHelper {


    public static boolean isCustomeStatus(int code) {

        return code == Config.NetStatus.STATUS_1
                || code == Config.NetStatus.STATUS_2
                || code == Config.NetStatus.STATUS_3
                || code == Config.NetStatus.STATUS_4
                || code == Config.NetStatus.STATUS_5
                || code == Config.NetStatus.STATUS_6
                || code == Config.NetStatus.STATUS_7;

    }

    public static String getStatusMsg(Response response) {
        String msg = null;
        int code = response.code();
        if (code == 400) {
            msg = "路径或者参数错误";
        } else if (code == 500) msg = "服务器不能处理请求";

        else msg = response.message();
        return msg;

    }

    public static BaseResponse parseErrorBody(Response response){
        try {
            String string = response.errorBody().string();
            return new Gson().fromJson(string, BaseResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;



    }



}
