package com.chenkaihua.oneschedule.net;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by chenkh on 16-4-2.
 */

public interface FileService {


    /**
     * 通过 List<MultipartBody.Part> 传入多个part实现多文件上传
     * @param parts 每个part代表一个
     * @return 状态信息
     */
    @Multipart
    @POST("users/image")
    BaseResponse<String> uploadFilesWithParts(@Part() List<MultipartBody.Part> parts);


    /**
     * 通过 MultipartBody和@body作为参数来上传
     * @param multipartBody MultipartBody包含多个Part
     * @return 状态信息
     */
    @POST("users/image")
    BaseResponse<String> uploadFileWithRequestBody(@Body MultipartBody multipartBody);


}
