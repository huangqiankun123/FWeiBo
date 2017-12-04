package com.qiankun.fweibo.core;


import com.blankj.utilcode.util.NetworkUtils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/11/15.
 * okhttp 请求缓存
 */

public class CacheInterceptor implements Interceptor{

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!NetworkUtils.isAvailableByPing()){
            request =request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
        }
        Response proceed = chain.proceed(request);
        if (!NetworkUtils.isAvailableByPing()){
            //有网络 设置缓存为默认值
            String cacheControl = request.cacheControl().toString();
            return proceed.newBuilder()
                    .header("Cache-Control",cacheControl)
                    .removeHeader("Pragma")
                    .build();
        }else {
            // 无网络时 设置超时为1周
            int maxStale = 60 * 60 * 24 * 7;
            return proceed.newBuilder()
                    .header("Cache-Control","public, only-if-cached, max-stale=" + maxStale)
                    .removeHeader("Pragma")
                    .build();
        }
    }
}
