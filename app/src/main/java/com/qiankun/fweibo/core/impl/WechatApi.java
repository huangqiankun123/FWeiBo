package com.qiankun.fweibo.core.impl;

import com.qiankun.fweibo.core.BaseResponse;
import com.qiankun.fweibo.modules.WechatBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by QKun on 2017/12/4.
 */

public interface WechatApi {
    @GET("weixin/query")
    Observable<BaseResponse<WechatBean>> getWechat(@Query("key") String appkey,
                                       @Query("pno") int pno,
                                       @Query("ps") int ps);
}
