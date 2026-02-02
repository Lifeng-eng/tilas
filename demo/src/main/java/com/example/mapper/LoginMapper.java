package com.example.mapper;

import com.example.dto.LoginInfoDTO;
import com.example.entity.Emp;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {

    LoginInfoDTO login(Emp emp);
}
