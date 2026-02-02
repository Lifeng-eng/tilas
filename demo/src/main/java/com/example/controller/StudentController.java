package com.example.controller;

import com.example.dto.StudentResponeDTO;
import com.example.dto.StudentsQueryParam;
import com.example.common.PageResult;
import com.example.common.Result;
import com.example.entity.Student;
import com.example.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/students")
@Slf4j
public class StudentController {
    @Autowired
    private StudentService studentService;

    /*
     * 分页查询学员
     * */
    @GetMapping
    public Result<PageResult<Student>> StudentQuery(StudentsQueryParam studentsQueryParam) {
        log.info("分页查询 {}", studentsQueryParam);
        return Result.success(studentService.stuQuery(studentsQueryParam));
    }

    /*
     * 删除学员信息
     * 参数为数组
     * */
    @DeleteMapping("/{ids}")
    public Result<Void> delete(@PathVariable("ids") String ids) {
        log.info("删除学员信息 {}", ids);
        // 将逗号分隔的字符串转换为Integer数组
        String[] idArray = ids.split(",");
        Integer[] idIntegers = Arrays.stream(idArray)
                .map(Integer::parseInt)
                .toArray(Integer[]::new);
        studentService.deleteById(idIntegers);
        return Result.success(null);
    }

    /*
     * 新增学员信息
     * */
    @PostMapping
    public Result<Void> add(@RequestBody Student student) {
        log.info("新增学员信息 {}", student);
        studentService.add(student);
        return Result.success(null);
    }

    /*
     * 根据ID查询学员信息
     * */
    @GetMapping("/{id}")
    public Result<StudentResponeDTO> findById(@PathVariable Integer id) {
        log.info("根据ID查询学员信息 {}", id);
        StudentResponeDTO studentResponeDTO = studentService.findById(id);
        return Result.success(studentResponeDTO);
    }

    /*
     * 修改学员信息
     * */
    @PutMapping
    public Result<Void> update(@RequestBody Student student) {
        log.info("修改学员信息 {}", student);
        studentService.update(student);
        return Result.success(null);
    }

    /*
     * 违纪处理 - 扣分
     * @param id 学员ID
     * @param score 扣分数值
     */
    @PutMapping("/violation/{id}/{score}")
    public Result<Void> violation(@PathVariable Integer id, @PathVariable Integer score) {
        log.info("学员违纪处理 - ID: {}, 扣分: {}", id, score);
        studentService.violation(id, score);
        return Result.success(null);
    }


}
