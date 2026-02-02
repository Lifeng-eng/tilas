package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/*
* 登录返回实体类
* */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginInfoDTO {

    /*
    * 用户名
    * */
    private Integer id;
    private String username;
    private String name;
    private String token;
}
