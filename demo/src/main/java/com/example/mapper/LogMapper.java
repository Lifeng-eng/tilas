// LogMapper.java
package com.example.mapper;

import com.example.pojo.OperateLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LogMapper {

    // 查询操作日志列表
    @Select("SELECT * FROM operate_log ORDER BY operate_time DESC")
    List<OperateLog> list();
}
