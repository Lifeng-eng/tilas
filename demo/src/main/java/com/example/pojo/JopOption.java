package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//员工职位数据统计类
public class JopOption {

    private List jopList;//职位列表

    private List dataList ;//人数列表

}
