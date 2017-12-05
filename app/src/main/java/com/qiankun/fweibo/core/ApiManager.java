package com.qiankun.fweibo.core;

import com.qiankun.fweibo.config.HttpConfig;
import com.qiankun.fweibo.core.impl.ApiService;
import com.qiankun.fweibo.core.impl.WechatApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by QKun on 2017/12/4.
 * 简单的Retrofit 的单例、、
 * 拦截器部分存在放在另一个类中后者是在拦截器工具类中主要有 token logger cache 等
 */

public class ApiManager {
    private static ApiManager apiManager;
    private ApiService mApiService;
    private WechatApi mWechatApi;


    private ApiManager() {
    }

    public static ApiManager getApiManager() {
        if (apiManager == null) {
            synchronized (ApiManager.class) {
                if (apiManager == null) {
                    apiManager = new ApiManager();
                }
            }
        }
        return apiManager;
    }


    private static OkHttpClient okHttpClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS)
                .writeTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS)
                .readTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS)
                .addInterceptor(InterceptorUtil.LogInterceptor())
                .build();
        return client;
    }

    /**
     * demo  可以多个baseUrl
     */
    public ApiService getZhiHuService() {
        if (mApiService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(HttpConfig.ZHIHU_API_URL)
                    .client(okHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            mApiService = retrofit.create(ApiService.class);
        }
        return mApiService;
    }

    /**
     * 微信精选API
     *
     * @return
     */
    public WechatApi getWechatApi() {
        if (mWechatApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(HttpConfig.WEIXIN_API_URL)
                    .client(okHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            mWechatApi = retrofit.create(WechatApi.class);
        }
        return mWechatApi;
    }


}
