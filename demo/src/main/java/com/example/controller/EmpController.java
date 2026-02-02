package com.example.controller;

import com.example.common.PageResult;
import com.example.common.Result;
import com.example.dto.EmpQueryParam;
import com.example.dto.EmpResponeDTO;
import com.example.dto.EmpResponseByIdDTO;
import com.example.entity.Emp;
import com.example.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    /*
    * 分页查询
    * */
    @GetMapping
    public Result<PageResult<EmpResponeDTO>> page(EmpQueryParam empQueryParam) {
        log.info("分页查询 {}", empQueryParam);
        return Result.success(empService.page(empQueryParam));
    }
    /*
    * 保存员工的基本信息
    * */
    @PostMapping
    public Result<Void> save(@RequestBody Emp emp){
        log.info("保存员工信息 {}", emp);
        empService.save(emp);
        return Result.success(null);
    }
    /*
    * 删除员工信息
    * 参数为数组
    * */
    @DeleteMapping
    public Result<Void> delete(Integer[] ids){
        log.info("删除员工信息 {}", (Object) ids);
        empService.deleteById(ids);
        return Result.success();
    }
    /*
    * 根据id查询员工信息
    * */
    @GetMapping("/{id}")
    public Result<EmpResponseByIdDTO> findById(@PathVariable Integer id) {
        log.info("根据id查询员工信息 {}", id);
        return Result.success(empService.findById(id));
    }
    /*
    * 修改员工信息
    * */
    @PutMapping
    public Result<Void> update(@RequestBody Emp emp) {
        log.info("修改员工信息 {}", emp);
        empService.updateById(emp);
        return Result.success(null);
    }


}
