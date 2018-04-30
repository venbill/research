package com.yuwb.domain;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yuwb on 2017/9/11.
 * api返回信息
 */
public class RtData {
    private int code;
    private String msg;
    private Object data;

    public RtData() {
        super();
        this.setCode(200);
        this.setMsg("");
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    public RtData setData(Object obj) {
        data = obj;
        return this;
    }

    public RtData setData(String key, Object value) {
        if (data != null && data instanceof Map) {
            ((Map) data).put(key, value);
        } else {
            data = new HashMap<String, Object>();
            ((Map) data).put(key, value);
        }
        return this;
    }

    public RtData setResults(Object list) {
        return setData("results", list);
    }

    public RtData setTotal(long value) {
        return setData("total", value);
    }

    public RtData setCount(int value) {
        return setData("count", value);
    }

    public RtData setList(List list) {
        if (list instanceof org.springframework.data.domain.Page) {
            org.springframework.data.domain.Page page = (org.springframework.data.domain.Page) list;
            setTotal(page.getTotalElements());
            setCount(page.getNumberOfElements());
            setResults(page.getContent());
        } else {
            setResults(list);
        }
        return this;
    }
}
