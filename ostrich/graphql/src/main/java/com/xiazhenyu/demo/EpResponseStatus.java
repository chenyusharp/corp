package com.xiazhenyu.demo;

import lombok.Data;

/**
 * Date: 2023/12/15
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
//@Data
public class EpResponseStatus {

    private String traceId;
    private String code = "1";
    private String msg = "操作成功";
    private Object data;

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}