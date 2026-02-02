package com.example.service.impl;

import com.example.mapper.EmpMapper;
import com.example.mapper.StudentMapper;
import com.example.pojo.JopOption;
import com.example.pojo.StudentOption;
import com.example.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private StudentMapper studentMapper;
    @Override
    public JopOption empJobData() {

        List<Map<String,Object>> list = empMapper.empJobData();

        List<String> jopList = list.stream().map(item -> item.get("pos").toString()).toList();
        List<Integer> dataList = list.stream().map(item -> Integer.parseInt(item.get("num").toString())).toList();

        return new JopOption(jopList,dataList);
    }

    @Override
    public List<Map<String, Object>> empGenderData() {

        return empMapper.empGenderData();
    }

    /*
    * 统计员工学历人数
    * */
    @Override
    public List<Map<String, Object>> studentDegreeData() {

        return studentMapper.studentDegreeData();
    }

    /*
    * 统计班级人数
    * */
    @Override
    public StudentOption studentCountData() {

        List<Map<String, Object>> clazzList = studentMapper.list();

        List<String> clazzNameList = clazzList.stream().map(item -> item.get("name").toString()).toList();

        List<Integer> dataList = clazzList.stream().map(item -> Integer.parseInt(item.get("num").toString())).toList();

        return new StudentOption(clazzNameList,dataList);
    }
}
