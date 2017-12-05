package com.qiankun.fweibo.core;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;


import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.JsonParseException;
import com.qiankun.fweibo.R;
import com.qiankun.fweibo.utils.CommonDialogUtils;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

import static com.qiankun.fweibo.core.BaseObserver.ExceptionReason.CONNECT_ERROR;
import static com.qiankun.fweibo.core.BaseObserver.ExceptionReason.CONNECT_TIMEOUT;
import static com.qiankun.fweibo.core.BaseObserver.ExceptionReason.PARSE_ERROR;
import static com.qiankun.fweibo.core.BaseObserver.ExceptionReason.UNKNOWN_ERROR;

/**
 * Created by Administrator on 2017/11/16.
 */

public abstract class BaseObserver<T> implements Observer<BaseResponse<T>> {

    private static final String TAG = "BaseObserver";
    private Context mContext;
    private CommonDialogUtils dialogUtils;

    public BaseObserver(Context context) {
        this.mContext = context;
        dialogUtils = new CommonDialogUtils();
        dialogUtils.showProgress(mContext);
    }

    public BaseObserver(Context context, boolean isShowLoading) {
        this.mContext = context;
        dialogUtils = new CommonDialogUtils();
        if (isShowLoading) {
            dialogUtils.showProgress(mContext, "Loading...");
        }
    }




    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(BaseResponse<T> value) {
        dismissProgress();
        if (value.isSuccess()) {
            T t = value.getResult();
            onSuccess(t);
        } else {
            onFail(value.getMsg());
        }
    }

    private void dismissProgress() {
        if (dialogUtils != null) {
            dialogUtils.dismissProgress();
        }
    }

    protected void onFail(String msg) {
        if (TextUtils.isEmpty(msg)) {
            ToastUtils.showLong(R.string.response_return_error);
        } else {
            ToastUtils.showLong(msg);
        }
    }

    protected abstract void onSuccess(T t);

    @Override
    public void onError(Throwable e) {
//    先不用代码保存
//        ApiException apiException = ExceptionEngine.handleException(e);
//        ToastUtils.showShort(apiException.getMsg());
        LogUtils.e("http", e.getMessage());
        if (e instanceof HttpException) {     //   HTTP错误
            onException(ExceptionReason.BAD_NETWORK);
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {   //   连接错误
            onException(CONNECT_ERROR);
        } else if (e instanceof InterruptedIOException) {   //  连接超时
            onException(CONNECT_TIMEOUT);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {   //  解析错误
            onException(PARSE_ERROR);
        } else {
            onException(UNKNOWN_ERROR);
        }
    }

    @Override
    public void onComplete() {

    }


    /**
     * 请求异常
     *
     * @param reason
     */
    public void onException(ExceptionReason reason) {
        switch (reason) {
            case CONNECT_ERROR:
                ToastUtils.showLong(R.string.connect_error);
                break;

            case CONNECT_TIMEOUT:
                ToastUtils.showLong(R.string.connect_timeout);
                break;

            case BAD_NETWORK:
                ToastUtils.showLong(R.string.bad_network);
                break;

            case PARSE_ERROR:
                ToastUtils.showLong(R.string.parse_error);
                break;

            case UNKNOWN_ERROR:
            default:
                ToastUtils.showLong(R.string.unknown_error);
                break;
        }
    }


    /**
     * 请求网络失败原因
     */
    public enum ExceptionReason {
        /**
         * 解析数据失败
         */
        PARSE_ERROR,
        /**
         * 网络问题
         */
        BAD_NETWORK,
        /**
         * 连接错误
         */
        CONNECT_ERROR,
        /**
         * 连接超时
         */
        CONNECT_TIMEOUT,
        /**
         * 未知错误
         */
        UNKNOWN_ERROR,
    }
}
