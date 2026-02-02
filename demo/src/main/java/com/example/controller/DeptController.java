package com.example.controller;

import com.example.anno.LogOperation;
import com.example.common.Result;
import com.example.entity.Dept;
import com.example.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {

    /*
     *
     * */

    @Autowired
    private DeptService deptService;

    @GetMapping
    public Result<List<Dept>> list() {
        log.info("查询所有部门");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    @LogOperation
    @DeleteMapping
    public Result<Void> delete(Integer id) {
        log.info("根据ID删除部门 {}", id);
        try {
            deptService.deleteById(id);
            return Result.success();
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }


    //新增部门
    @LogOperation
    @PostMapping
    public Result<Void> add(@RequestBody Dept dept) {
        log.info("新增部门 {}", dept);
        deptService.add(dept);
        return Result.success(null);
    }

    /*
     * 根据id查询部门
     * */
    @GetMapping("/{id}")
    public Result<Dept> findById(@PathVariable Integer id) {
        log.info("根据id查询部门 {}", id);
        Dept dept = deptService.findById(id);
        return Result.success(dept);
    }

    /*
     * 根据id修改部门
     * */
    @LogOperation
    @PutMapping
    public Result<Void> update(@RequestBody Dept dept) {
        log.info("修改部门 {}", dept);
        deptService.update(dept);
        return Result.success(null);
    }

}
