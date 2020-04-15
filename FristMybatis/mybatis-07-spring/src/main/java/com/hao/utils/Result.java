package com.hao.utils;

/**
 * @author shidacaizi
 * @date 2020/4/15 19:23
 */
public class Result {
    //状态码
    private int status;

    //消息
    private String message;

    //返回数据
    private Object data;

    public Result(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    //成功
    public static Result success(Object data) {
        return new Result(ResponseStatusConstant.RESPONSE_STATUS_SUCCESS, "success", data);
    }

    public static Result success(String message) {
        return new Result(ResponseStatusConstant.RESPONSE_STATUS_SUCCESS, message, null);
    }

    public static Result success() {
        return new Result(ResponseStatusConstant.RESPONSE_STATUS_SUCCESS, "success", null);
    }

    //失败
    public static Result fail() {
        return new Result(ResponseStatusConstant.RESPONSE_STATUS_FAIL, "fail", null);
    }

    public static Result fail(Object data) {
        return new Result(ResponseStatusConstant.RESPONSE_STATUS_FAIL, "fail", data);
    }

    public static Result fail(String message) {
        return new Result(ResponseStatusConstant.RESPONSE_STATUS_FAIL, message, null);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
