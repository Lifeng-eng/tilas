package com.example.controller;

import com.example.common.PageResult;
import com.example.common.Result;
import com.example.dto.ClazzQueryDTO;
import com.example.dto.ClazzQueryParam;
import com.example.entity.Clazz;
import com.example.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    /*
    * 查询所有的班级列表
    * */
    @GetMapping
    public Result<PageResult<Clazz>> page(ClazzQueryParam clazzQueryParam){
        log.info("分页查询 {}", clazzQueryParam);
        return Result.success(clazzService.page(clazzQueryParam));
    }
    /*
    * 删除班级
    * */
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Integer id){
        log.info("删除班级 {}", id);
        clazzService.deleteById(id);
        return Result.success();
    }
    /*
    * 添加班级信息
    * */
    @PostMapping
    public Result<Void> add(@RequestBody Clazz clazz){
        log.info("添加班级信息 {}", clazz);
        clazzService.add(clazz);
        return Result.success();
    }
    /*
    * 修改班级信息
    * */
    @PutMapping
    public Result<Void> update(@RequestBody Clazz clazz){
        log.info("修改班级信息 {}", clazz);
        clazzService.updateById(clazz);
        return Result.success();
    }
    /*
    * 根据ID查询班级信息
    * */
    @GetMapping("/{id}")
    public Result<ClazzQueryDTO> findById(@PathVariable Integer id) {
        log.info("根据ID查询班级信息 {}", id);
        return Result.success(clazzService.findById(id));
    }
    /*
    * 查询所有班级信息
    * */
    @GetMapping("/list")
    public Result<List<ClazzQueryDTO>> list() {
        log.info("查询所有班级信息");
        return Result.success(clazzService.list());
    }


}
