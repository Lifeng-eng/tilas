package com.example.controller;

import com.example.common.Result;
import com.example.dto.LoginInfoDTO;
import com.example.entity.Emp;
import com.example.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 *
 * 登录接口
 * */
@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {


    @Autowired
    private LoginService loginService;

    @PostMapping
    public Result<LoginInfoDTO> login(@RequestBody Emp emp) {
        log.info("员工登录 {}", emp);

        /*
         * 校验一下用户名或密码是否为空
         * */
        if (emp.getUsername() == null || emp.getUsername().isEmpty() || emp.getPassword() == null || emp.getPassword().isEmpty())
            return Result.error("用户名或密码不能为空");
        LoginInfoDTO loginInfoDTO = loginService.login(emp);
        if (loginInfoDTO != null)
            return Result.success(loginInfoDTO);
        return Result.error("用户名或密码错误");
    }
}
