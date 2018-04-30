package com.yuwb.util;


import com.yuwb.domain.RtData;
import com.yuwb.enums.ErrorCode;

public abstract class ResponseBuilder {

    private ResponseBuilder() {
    }

    public static RtData ok() {
        return build(200, "操作成功", null);
    }

    public static RtData failure(int code) {
        return build(code, "操作失败", null);
    }

    public static RtData fail(ErrorCode errorCode,String msg) {
        return build(errorCode.getCode(), msg, null);
    }


    public static RtData notSuccess(String msg) {
        return build(ErrorCode.NOT_SUCCESS.getCode(), msg, null);
    }

    public static RtData ok(Object data) {
        return build(200, "操作成功", data);
    }

    public static RtData ok(String msg) {
        return build(200, msg, null);
    }

    public static RtData ok(String msg, Object data) {
        return build(200, msg, data);
    }

    public static RtData build() {
        return build(0, null, null);
    }

    public static RtData build(ErrorCode errorCode) {
        return build(errorCode.getCode(), errorCode.getErrMsg(), null);
    }



    public static RtData build(int code, String msg) {
        return build(code, msg, null);
    }

    public static RtData build(int code, String msg, Object data) {
        RtData resp = new RtData();
        resp.setCode(code);
        resp.setMsg(msg);
        resp.setData(data);
        return resp;
    }
}
