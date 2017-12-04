package com.qiankun.fweibo.core;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/11/16.
 */

public class BaseResponse<T> {
    @SerializedName("msg")
    private String msg;

    @SerializedName("retCode")
    private int code;

    @SerializedName("result")
    private T result;

    public boolean isSuccess() {
        return code == 200 ? true : false;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
