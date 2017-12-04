package com.qiankun.fweibo.core;

import com.google.gson.Gson;

import io.reactivex.annotations.Nullable;
import io.reactivex.functions.Function;

/**
 * Created by Administrator on 2017/11/21.
 */

public class ServerResultFunction implements Function<BaseResponse, Object> {
    @Nullable
    @Override
    public Object apply(@Nullable BaseResponse input) {
        //打印服务器回传结果
//        LogUtils.e(response.toString());
        if (!input.isSuccess()) {
            throw new ServerException(input.getCode(), input.getMsg());
        }
        return new Gson().toJson(input.getResult());
    }
}
