package com.example.mapper;

import com.example.dto.EmpQueryParam;
import com.example.dto.EmpResponeDTO;
import com.example.dto.EmpResponseByIdDTO;
import com.example.entity.Emp;
import com.example.entity.EmpExpr;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {


    /*
    * 分页查询
    * */


    public List<EmpResponeDTO> page(EmpQueryParam empQueryParam);

    /*
    * 保存员工信息
    * */


    void save(Emp emp);

    /*
    * 保存员工工作经历信息
    * */
    void saveExpr(List<EmpExpr> exprList);

    /*
    * 删除员工信息
    * */
    void deleteById(Integer[] ids);
    /*
    * 删除员工工作经历信息
    * */
    void deleteExprByEmpId(Integer[] ids);

    /*
    * 根据ID查询员工信息
    * */
    EmpResponseByIdDTO findById(Integer id);

    /*
    * 修改员工信息
    * */
    void updateById(Emp emp);

    /*
    * 统计员工职位人数
    * */
    @MapKey("pos")
    List<Map<String, Object>> empJobData();

    /*
    * 统计员工性别人数
    * */
    @MapKey("name")
    List<Map<String, Object>> empGenderData();


}
