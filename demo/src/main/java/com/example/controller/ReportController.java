package com.example.controller;

import com.example.common.Result;
import com.example.pojo.JopOption;
import com.example.pojo.StudentOption;
import com.example.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    /*
    * 统计员工职位人数
    * */
    @GetMapping("/empJobData")
    public Result<JopOption> empJobData(){
        log.info("统计员工职位人数");
        JopOption jopOption = reportService.empJobData();
        return Result.success(jopOption);
    }
    /*
    * 统计员工性别人数
    * */
    @GetMapping("/empGenderData")
    public Result<List<Map<String, Object>>>empGenderData(){
        log.info("统计员工性别人数");

        List<Map<String, Object>> list = reportService.empGenderData();
        return Result.success(list);
    }
    /*
    * 统计学员学历人数
    * */
    @GetMapping("/studentDegreeData")
    public Result<List<Map<String, Object>>> studentDegreeData(){
        log.info("统计学员学历人数");

        List<Map<String, Object>> list = reportService.studentDegreeData();
        return Result.success(list);
    }
    /*
    * 统计各个班级人数
    * */
    @GetMapping("/studentCountData")
    public Result<StudentOption> studentCountData(){
        log.info("统计各个班级人数");
        StudentOption studentOption = reportService.studentCountData();
        return Result.success(studentOption);

    }




}
