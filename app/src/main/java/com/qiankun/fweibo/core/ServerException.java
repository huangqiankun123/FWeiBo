package com.qiankun.fweibo.core;

/**
 * Created by Administrator on 2017/11/16.
 * 那么我们就可以在解析服务端返回数据的时候，当code!=0，就抛出ServerException
 */

public class ServerException extends RuntimeException {
    private int code;
    private String msg;

    public ServerException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
