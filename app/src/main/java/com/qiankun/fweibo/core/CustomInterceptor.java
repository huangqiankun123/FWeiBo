package com.qiankun.fweibo.core;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/8/28.
 */

class CustomInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request()
                .newBuilder()
                .header("User-agent", "Mozilla/4.0")

                .build();

//                        .addHeader("Content-Type", "application/json; charset=UTF-8")
//                .addHeader("Accept-Encoding", "gzip, deflate")
//                .addHeader("Connection", "keep-alive")
//                .addHeader("Accept", "**/*//*")
//                .addHeader("Cookie", "add cookies here")
//
//                .addHeader("x-noncestr", Config.noncestr)
//                .addHeader("x-token", Config.token)
//                .addHeader("x-timestamp", Config.timeMillis)
//                .addHeader("x-sign", TransfmtUtils.getMD5(Config.x_sign))

        return chain.proceed(request);
    }
}
