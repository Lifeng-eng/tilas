package com.example.exception;


import com.example.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//全局异常处理类
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public Result<Void> error(Exception e) {
        log.error("服务器异常 {}", e);
        return Result.error("服务器异常");
    }

    /*
     * 处理值重复的异常
     * */
    @ExceptionHandler
    public Result<Void> handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("数据库操作异常 {}", e);

        String message = e.getMessage();
        String errorMessage = message.substring(message.lastIndexOf(":") + 1).trim();

        // 根据不同的唯一约束返回不同的错误信息
        if (errorMessage.contains("phone")) {
            return Result.error("手机号已存在");
        } else if (errorMessage.contains("username")) {
            return Result.error("用户名已存在");
        } else {
            return Result.error("数据已存在，违反唯一约束");
        }
    }
    @ExceptionHandler
    public Result<Void> handleRuntimeException(RuntimeException e) {
        log.error("业务异常 {}", e.getMessage());
        return Result.error(e.getMessage());
    }

}
