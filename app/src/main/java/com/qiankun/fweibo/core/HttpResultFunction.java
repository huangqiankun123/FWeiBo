package com.qiankun.fweibo.core;


import io.reactivex.Observable;
import io.reactivex.annotations.Nullable;
import io.reactivex.functions.Function;

/**
 * Created by Administrator on 2017/11/21.
 */

public class HttpResultFunction<T> implements Function<Throwable,Observable<T>> {

    @Nullable
    @Override
    public Observable<T> apply(@Nullable Throwable input) {
        //打印具体错误
//        LogUtils.e("HttpResultFunction:" + throwable);
        return Observable.error(ExceptionEngine.handleException(input));
    }
}
