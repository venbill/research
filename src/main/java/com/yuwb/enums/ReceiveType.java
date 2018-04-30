package com.yuwb.enums;

/**
 * @Author: BillYu
 * @Description:发布状态
 * @Date: Created in 下午2:06 2018/4/17.
 */
public enum ReceiveType {
    NOT_PUBLISH(1,"未发布"),
    PUBLISH(2,"发布"),
    PAUSE(3,"暂停"),
    CLOSE(4,"关闭");

    private Integer code;
    private String name;

    ReceiveType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }


    public static ReceiveType valueOf(Integer type) {
        ReceiveType[] values = ReceiveType.values();
        for (ReceiveType object : values) {
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
