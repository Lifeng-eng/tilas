package com.example.dto;

import lombok.Data;

//学员管理请求查询封装类
@Data
public class StudentsQueryParam {

    private String name;
    private Integer degree;
    private Integer clazzId;
    private Integer page = 1;
    private Integer pageSize = 10;
}
