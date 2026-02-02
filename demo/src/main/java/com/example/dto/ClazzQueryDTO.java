package com.example.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/*
* 用于返回根据id查询班级信息和返回所有班级信息
* */
@Data
public class ClazzQueryDTO {
    private Integer id; //ID
    private String name; //班级名称
    private String room; //班级教室
    private LocalDate beginDate; //开课时间
    private LocalDate endDate; //结课时间
    private Integer masterId; //班主任
    private Integer subject; //学科
    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //修改时间
}
