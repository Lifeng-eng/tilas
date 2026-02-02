package com.example.service.impl;

import com.example.common.PageResult;
import com.example.dto.StudentResponeDTO;
import com.example.dto.StudentsQueryParam;
import com.example.entity.Student;
import com.example.mapper.StudentMapper;
import com.example.service.StudentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper empMapper;
    @Override
    public PageResult<Student> stuQuery(StudentsQueryParam studentsQueryParam) {

        //使用pageHelper插件进行分页处理
        PageHelper.startPage(studentsQueryParam.getPage(), studentsQueryParam.getPageSize());

        //执行查询获取学员列表
        List<Student> list = empMapper.stuQuery(studentsQueryParam);

        //将查询结果转换为Page对象以获取分页信息
        Page<Student> empPage = (Page<Student>) list;
        return new PageResult<>(empPage.getTotal(), list);
    }

    /*
    * 批量删除学员
    * */
    @Override
    public void deleteById(Integer[] ids) {

        empMapper.deleteById(ids);
    }

    /*
    * 新增学员
    * */
    @Override
    public void add(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        student.setViolationCount((short) 0);
        student.setViolationScore((short) 0);
        empMapper.add(student);
    }

    /*
    * 根据ID查询学员信息
    * */

    @Override
    public StudentResponeDTO findById(Integer id) {

        return empMapper.findById(id);
    }
    /*
    * 根据ID修改学员信息
    * */

    @Override
    public void update(Student student) {

        //更新时间
        student.setUpdateTime(LocalDateTime.now());
        empMapper.update(student);
    }
    /*
    * 违纪处理 - 扣分
    * */

    @Override
    public void violation(Integer id, Integer score) {

        empMapper.violation(id, score);
    }
}
