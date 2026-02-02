package com.example.mapper;

import com.example.dto.StudentResponeDTO;
import com.example.dto.StudentsQueryParam;
import com.example.entity.Student;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    List<Student> stuQuery(StudentsQueryParam studentsQueryParam);

    void deleteById(Integer[] ids);

    void add(Student student);

    StudentResponeDTO findById(Integer id);

    void update(Student student);

    void violation(Integer id, Integer score);

    @MapKey("name")
    List<Map<String, Object>> studentDegreeData();

    @MapKey("name")
    List<Map<String, Object>> list();
}
