package com.yuwb.enums;

/**
 * @Author: BillYu
 * @Description:
 * @Date: Created in 下午2:40 2018/4/17.
 */
public class EnumModel {
    private Integer code;
    private String name;


    public EnumModel(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
