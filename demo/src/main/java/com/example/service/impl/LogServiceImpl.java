// LogServiceImpl.java
package com.example.service.impl;

import com.example.mapper.LogMapper;
import com.example.pojo.OperateLog;
import com.example.service.LogService;
import com.example.common.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public PageResult<OperateLog> page(Integer page, Integer pageSize) {
        // 使用 try-with-resources 确保 Page 资源正确清理
        try (Page<OperateLog> logPage = PageHelper.startPage(page, pageSize)) {
            // 执行查询
            List<OperateLog> logList = logMapper.list();

            // 返回封装的结果
            return new PageResult<>(logPage.getTotal(), logList);
        }
    }
}