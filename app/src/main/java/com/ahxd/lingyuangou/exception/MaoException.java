package com.ahxd.lingyuangou.exception;

/**
 * Created by Mao on 2018/1/17.
 */

public class MaoException extends Exception {

    private int mCode;
    private String mMessage;

    public MaoException(String message) {
        super(message);
        this.mMessage = message;
    }

    public MaoException(int code, String message) {
        super(message);
        this.mCode = code;
        this.mMessage = message;
    }

    public String getMessage() {
        return mMessage;
    }

    public int getCode() {
        return mCode;
    }

}
