// LogController.java
package com.example.controller;

import com.example.common.PageResult;
import com.example.common.Result;
import com.example.pojo.OperateLog;
import com.example.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logService;

    /*
     * 日志列表分页查询
     * */
    @GetMapping("/page")
    public Result<PageResult<OperateLog>> log(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize) {

        PageResult<OperateLog> pageResult = logService.page(page, pageSize);
        return Result.success(pageResult);
    }
}
