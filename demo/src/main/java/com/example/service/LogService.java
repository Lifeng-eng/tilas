// LogService.java
package com.example.service;

import com.example.common.PageResult;
import com.example.pojo.OperateLog;

public interface LogService {

    // 分页查询操作日志
    PageResult<OperateLog> page(Integer page, Integer pageSize);
}
