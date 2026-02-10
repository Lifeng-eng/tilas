package com.example.service;

import com.example.common.PageResult;
import com.example.dto.EmpQueryParam;
import com.example.dto.EmpResponeDTO;
import com.example.dto.EmpResponseByIdDTO;
import com.example.entity.Emp;

public interface EmpService {

    PageResult<EmpResponeDTO> page(EmpQueryParam empQueryParam);

    void save(Emp emp);

    void deleteById(Integer[] ids);

    EmpResponseByIdDTO findById(Integer id);

    void updateById(Emp emp);
}
