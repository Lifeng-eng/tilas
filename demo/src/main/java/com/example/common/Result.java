package com.example.common;

import lombok.Getter;

// 静态工厂方法模式，创建对象，返回Result对象
@Getter// 生成getter方法，用于序列化
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    // 私有化构造方法
    private Result() {
    }


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