package com.cymjoe.moudle_base.base;

/**
 * version：v1.0
 */


public class APIException extends Exception {
    private int code;
    private String message;

    /**
     * Http异常处理
     *
     * @param code    异常编码
     * @param message 错误信息
     */
    public APIException(int code, String message) {
        super(new Throwable(message));
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


}
