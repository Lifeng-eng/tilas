package com.example.service.impl;

import com.example.entity.Dept;
import com.example.mapper.DeptMapper;
import com.example.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public int countEmpByDeptId(Integer deptId) {
        return deptMapper.countEmpByDeptId(deptId);
    }
    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Override
    public void deleteById(Integer id) {

        // 检查部门下是否有员工
        int empCount = deptMapper.countEmpByDeptId(id);
        if (empCount > 0) {
            throw new RuntimeException("对不起，当前部门下有员工，不能直接删除！");
        }
        deptMapper.deleteById(id);
    }

    @Override
    public void add(Dept dept) {

        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.add(dept);
    }

    @Override
    public Dept findById(Integer id) {
        Dept dept = deptMapper.findById(id);

        return dept;
    }

    @Override
    public void update(Dept dept) {

        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}
