package com.min.spring.exception;

public enum MyErrorCode implements IMyErrorCode{

    DELETE_FAIL(1001,"删除失败"),
    COMMENT_NOT_FOUND(1002,"回复不存在"),
    USER_NOT_SAME(1003,"用户不一致,无法保存")
    ;

    private Integer code;
    private String message;

    //构造方法
    MyErrorCode(Integer code, String msg) {
        this.code = code;
        this.message = msg;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
