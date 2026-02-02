package com.example;

import com.example.mapper.EmpMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class MapperTest {

    @Autowired
    private EmpMapper empMapper;

    @Test
    public void testEmpGenderData() {

        List<Map<String, Object>> list = empMapper.empGenderData();
        System.out.println(list);

    }
}

