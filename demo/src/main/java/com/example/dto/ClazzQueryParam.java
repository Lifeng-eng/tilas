package com.example.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ClazzQueryParam {
    private Integer page = 1;
    private Integer pageSize = 10;
    private String name; // 班级名称
    private String room;
    private LocalDate begin; // 范围匹配的开始时间(结课时间)
    private LocalDate end;   // 范围匹配的结束时间(结课时间)
}
