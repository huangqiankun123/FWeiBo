package com.qiankun.fweibo.core.impl;

import com.qiankun.fweibo.ZhiHuDailyBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by QKun on 2017/12/4.
 */

public interface ApiService {

    //获取最近的日报
    @Headers("Cache-Control: public, max-age=86400")
    @GET("news/latest")
    Observable<ZhiHuDailyBean> getLatestZhihuDaily();
}
