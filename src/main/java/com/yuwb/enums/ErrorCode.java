package com.yuwb.enums;

/**
 * 错误代码及信息
 */
public enum ErrorCode {


    CREATE_ERROR(101, "创建失败"),

    READ_ERROR(102, "查询失败"),

    UPDATE_ERROR(103, "更新失败"),

    DELETE_ERROR(104, "删除失败"),

    ILLEGAL_ARGUMENT(105, "参数异常"),

    TIMETABLE_NOT_FOUND(106, "未找到作息时间"),

    USER_SCOPE_NOT_EXIST(107, "用户关系不存在"),

    MARK_ERROR(108, "标记失败"),

    //150-200 订单
    ORDER_NOT_EXIST(150, "订单不存在"),
    USER_ID_INCORRECT_FORMAT(151, "用户云号格式错误"),
    ORDER_AMOUNT_ERROR(152, "订单金额不正确"),
    USER_IS_NULL(153, "用户不存在"),
    BANDING_USER_ASSOCIATED_ID_NULL(154, "绑定人设备号不正确"),


    //意见反馈

    INVALID_PASSWORD(301, "密码错误"),

    ACCOUNT_NOT_EXIST(302, "帐号不存在"),

    ACCOUNT_DISABLED(304, "该帐号已被禁用"),

    INCORRECT_PHONE_NUMBER_FORMAT(303, "请输入正确的手机号"),



    ILLEGAL_REQUEST(400, "非法的请求"),

    NOT_AUTHORIZATION(401, "未授权"),

    INVALID_AUTHORIZATION(402, "授权认证失败"),

    EXPIRED_AUTHORIZATION(405, "授权过期"),
    REPEAT_COMMIT(410, "您已回答过该问卷，无法再次提交"),
    NOT_COMMIT(411, "尚未答过问卷，无法查看答案"),




    SERVER_ERROR(500, "服务器异常"),
    NOT_SUCCESS(403, "没有操作成功");






    private int code;

    private String errMsg;

    private ErrorCode(int code, String errMsg) {
        this.code = code;
        this.errMsg = errMsg;
    }

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


}
