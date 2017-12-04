package com.qiankun.fweibo.core;

import android.content.Context;
import android.widget.Toast;


import com.blankj.utilcode.util.ToastUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/11/16.
 */

public abstract class BaseObserver<T> implements Observer<BaseResponse<T>> {

    private static final String TAG = "BaseObserver";
    private Context mContext;

    public BaseObserver(Context context) {
        this.mContext = context;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(BaseResponse<T> value) {
        if (value.isSuccess()) {
            T t = value.getResult();
            onSuccess(t);
        } else {
            onHandleError(value.getMsg());
        }
    }

    protected void onHandleError(String msg) {
        ToastUtils.showShort(msg);
    }

    protected abstract void onSuccess(T t);

    @Override
    public void onError(Throwable e) {
        ApiException apiException = ExceptionEngine.handleException(e);
        ToastUtils.showShort(apiException.getMsg());
    }

    @Override
    public void onComplete() {

    }
}
