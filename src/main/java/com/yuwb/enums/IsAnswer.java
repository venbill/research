package com.yuwb.enums;

/**
 * @Author: BillYu
 * @Description:发布状态
 * @Date: Created in 下午2:06 2018/4/17.
 */
public enum IsAnswer {
    SELECTED(1,"选中"),
    NOT_SELECTED(0,"未选中");

    private Integer code;
    private String name;

    IsAnswer(Integer code, String name) {
        this.code = code;
        this.name = name;
    }


    public static IsAnswer valueOf(Integer type) {
        IsAnswer[] values = IsAnswer.values();
        for (IsAnswer object : values) {
            if (object.getCode().equals(type)) {
                return object;
            }
        }
        return null;
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
