package com.example.mapper;

import com.example.dto.ClazzQueryDTO;
import com.example.dto.ClazzQueryParam;
import com.example.entity.Clazz;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClazzMapper {
    /*
    * 分页查询所有班级
    * */
    List<Clazz> page(ClazzQueryParam clazzQueryParam);

    /*
    * 删除班级
    * */
    void deleteById(Integer id);

    /*
    * 添加班级
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
    * 查询所有班级
    * */
    List<ClazzQueryDTO> list();
}
