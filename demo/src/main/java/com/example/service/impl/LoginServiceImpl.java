package com.example.service.impl;

import com.example.dto.LoginInfoDTO;
import com.example.entity.Emp;
import com.example.mapper.LoginMapper;
import com.example.service.LoginService;
import com.example.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    /*
     * 登录
     * */
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public LoginInfoDTO login(Emp emp) {

        LoginInfoDTO loginInfoDTO = loginMapper.login(emp);
        /*
         * 检验是否存在该用户
         * */
        if (loginInfoDTO == null)
            return null;

        /*
         * 生成 JWT 令牌
         * */
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", loginInfoDTO.getId());
        claims.put("name", loginInfoDTO.getName());
        String token = JwtUtil.generateToken(claims, 60 * 60 * 1000);
        loginInfoDTO.setToken(token);
        return loginInfoDTO;
    }
}
