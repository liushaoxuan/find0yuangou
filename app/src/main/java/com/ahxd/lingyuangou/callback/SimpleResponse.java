package com.ahxd.lingyuangou.callback;

import java.io.Serializable;

public class SimpleResponse implements Serializable {

    public int status;
    public String msg;

    public MaoResponse toMaoResponse() {
        MaoResponse maoResponse = new MaoResponse();
        maoResponse.status = status;
        maoResponse.msg = msg;
        return maoResponse;
    }
}
