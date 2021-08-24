package com.xiazhenyu.protocol;

import java.io.Serializable;

/**
 * Date: 2021/8/16
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class Response implements Serializable {

    private int code;
    private String errMsg;
    private Object result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}