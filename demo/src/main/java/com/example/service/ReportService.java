package com.example.service;

import com.example.pojo.JopOption;
import com.example.pojo.StudentOption;

import java.util.List;
import java.util.Map;

public interface ReportService {

    JopOption empJobData();

    /*
    * 统计员工性别数据
    * */
    List<Map<String, Object>> empGenderData();

    /*
    * 统计学员学历数据
    * */
    List<Map<String, Object>> studentDegreeData();


    /*
    * 统计班级人数
    * */
    StudentOption studentCountData();

}
