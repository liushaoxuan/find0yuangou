package com.ahxd.lingyuangou.callback;

import java.io.Serializable;

/**
 * Created by Mao on 2017/10/27.
 */

public class MaoResponse<T> implements Serializable {

    public int status;
    public String msg;
    public T data;

    @Override
    public String toString() {
        return "MaoResponse {\n" +
                "\tstatus = " + status + "\n" +
                "\tmsg = " + msg + "\'\n" +
                "\tdata = " + data + "\n" +
                '}';
    }

}
