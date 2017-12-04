package com.qiankun.fweibo.core;

import com.qiankun.fweibo.config.HttpConfig;
import com.qiankun.fweibo.core.impl.ApiService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by QKun on 2017/12/4.
 */

public class ApiManager {
    private static ApiManager apiManager;
    private final OkHttpClient mClient;
    private ApiService mApiService;

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

    private ApiManager() {

        mClient = new OkHttpClient.Builder()
                .connectTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS)
                .readTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS)
                .writeTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS)
                .addInterceptor(InterceptorUtil.LogInterceptor()) //添加日志拦截器
//                .addNetworkInterceptor(new CacheInterceptor())  //缓存
                .build();

        //TODO 这是对一个BaseUrl 情况下 将所有的接口都放一个接口类中 最好的方式就都是分开来写
//        Retrofit retrofit =new Retrofit.Builder()
//                .baseUrl(HttpConfig.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .client(mClient)
//                .build();
//        ApiService apiService = retrofit.create(ApiService.class);


    }

    /**
     * demo TODO 可以多个baseUrl
     */
    public ApiService getZhiHuService() {
        if (mApiService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(HttpConfig.ZHIHU_API_URL)
                    .client(mClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            mApiService = retrofit.create(ApiService.class);
        }
        return mApiService;
    }

}
