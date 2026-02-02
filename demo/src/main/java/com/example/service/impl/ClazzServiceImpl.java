package com.example.service.impl;


import com.example.common.PageResult;
import com.example.dto.ClazzQueryDTO;
import com.example.dto.ClazzQueryParam;
import com.example.entity.Clazz;
import com.example.mapper.ClazzMapper;
import com.example.service.ClazzService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;

    /*
    * 分页查询班级
    * */
    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {

        // 使用PageHelper插件进行分页处理
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());

        // 执行查询获取班级列表
        List<Clazz> list = clazzMapper.page(clazzQueryParam);

        // 将查询结果转换为Page对象以获取分页信息
        Page<Clazz> empPage = (Page<Clazz>) list;

        // 构造并返回分页结果
        PageResult<Clazz> pageResult = new PageResult<Clazz>(empPage.getTotal(), empPage.getResult());

        //处理status
        for (Clazz clazz : pageResult.getRows()) {
            if (clazz.getEndDate() != null) {
                if (LocalDate.now().isAfter(clazz.getEndDate())) {
                    clazz.setStatus("已结课");
                } else if (clazz.getBeginDate() != null && LocalDate.now().isBefore(clazz.getBeginDate())) {
                    clazz.setStatus("未开班");
                } else {
                    clazz.setStatus("在读中");
                }
            }
        }
        return pageResult;



    }
    /*
    * 根据ID删除班级
    * */

    @Override
    public void deleteById(Integer id) {

        clazzMapper.deleteById(id);
    }

    /*
    * 添加班级
    * */
    @Override
    public void add(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());

        clazzMapper.add(clazz);
    }

    /*
    * 修改班级信息
    * */
    @Override
    public void updateById(Clazz clazz) {

        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.updateById(clazz);
    }

    /*
    * 根据ID查询班级信息
    * */
    @Override
    public ClazzQueryDTO findById(Integer id) {

        return clazzMapper.findById(id);
    }


    @Override
    public List<ClazzQueryDTO> list() {
        return clazzMapper.list();
    }
}
