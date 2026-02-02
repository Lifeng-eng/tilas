package com.example.service;

import com.example.common.PageResult;
import com.example.dto.ClazzQueryDTO;
import com.example.dto.ClazzQueryParam;
import com.example.entity.Clazz;

import java.util.List;

public interface ClazzService {
    /*
    * 分页查询所有班级
    * */
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    /*
    * 根据ID删除班级
    * */
    void deleteById(Integer id);

    /*
    * 保存班级信息
    * */
    void add(Clazz clazz);

    /*
    * 修改班级信息
    * */
    void updateById(Clazz clazz);

    /*
    * 根据ID查询班级信息
    * */
    ClazzQueryDTO findById(Integer id);

    /*
    * 查询所有班级信息
    * */
    List<ClazzQueryDTO> list();
}
