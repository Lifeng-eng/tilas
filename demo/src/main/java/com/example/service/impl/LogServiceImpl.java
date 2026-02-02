// LogServiceImpl.java
package com.example.service.impl;

import com.example.mapper.LogMapper;
import com.example.pojo.OperateLog;
import com.example.service.LogService;
import com.example.common.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public PageResult<OperateLog> page(Integer page, Integer pageSize) {
        // 设置分页参数
        PageHelper.startPage(page, pageSize);

        // 执行查询
        List<OperateLog> logList = logMapper.list();

        // 封装分页结果
        PageInfo<OperateLog> pageInfo = new PageInfo<>(logList);

        // 返回封装的结果
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }
}
