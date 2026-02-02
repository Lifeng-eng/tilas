package com.example.common;

import lombok.Data;

@Data
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    public static <T> Result<T> success() {
        return build(1, "success", null);
    }

    public static <T> Result<T> success(T data) {
        return build(1, "success", data);
    }

    public static <T> Result<T> error(String msg) {
        return build(0, msg, null);
    }

    public static <T> Result<T> build(Integer code, String msg, T data) {
        Result<T> result = new Result<>();
        result.code = code;
        result.msg = msg;
        result.data = data;
        return result;
    }

}