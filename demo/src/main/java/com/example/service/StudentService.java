package com.example.service;

import com.example.common.PageResult;
import com.example.dto.StudentResponeDTO;
import com.example.dto.StudentsQueryParam;
import com.example.entity.Student;

public interface StudentService {
    PageResult<Student> stuQuery(StudentsQueryParam studentsQueryParam);

    //删除学员
    void deleteById(Integer[] ids);

    void add(Student student);

    StudentResponeDTO findById(Integer id);

    void update(Student student);

    void violation(Integer id, Integer score);
}
