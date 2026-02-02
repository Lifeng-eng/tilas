package com.example.service;

import com.example.dto.LoginInfoDTO;
import com.example.entity.Emp;

public interface LoginService {
    /*
    * 员工登录
    * */
    LoginInfoDTO login(Emp emp);
}
