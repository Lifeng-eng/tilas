package com.example.service.impl;

import com.example.common.PageResult;
import com.example.dto.EmpQueryParam;
import com.example.dto.EmpResponeDTO;
import com.example.dto.EmpResponseByIdDTO;
import com.example.entity.Emp;
import com.example.entity.EmpExpr;
import com.example.entity.EmpLog;
import com.example.mapper.EmpMapper;
import com.example.service.EmpLogService;
import com.example.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工服务实现类
 */
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpLogService empLogService;

    /**
     * 分页查询员工信息
     *
     * @param empQueryParam 员工查询参数对象，包含页码、每页大小等查询条件
     * @return PageResult<Emp> 分页结果对象，包含总记录数和当前页的员工列表
     */
    @Override
    public PageResult<EmpResponeDTO> page(EmpQueryParam empQueryParam) {

        // 使用PageHelper插件进行分页处理
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());

        // 执行查询并在 try-with-resources 中关闭 Page，避免资源泄漏
        try (Page<EmpResponeDTO> empPage = (Page<EmpResponeDTO>) empMapper.page(empQueryParam)) {
            return new PageResult<EmpResponeDTO>(empPage.getTotal(), empPage.getResult());
        }
    }

    /**
     * 保存员工信息
     * @param emp 员工对象，包含员工信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(Emp emp) {
        try {
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.save(emp);

            //保存员工的工作经历
            List<EmpExpr> exprList = emp.getExprList();

            //判断是否有工作经历
            if(!exprList.isEmpty()){
                for (EmpExpr expr : exprList) {
                    expr.setEmpId(emp.getId());
                }
                empMapper.saveExpr(exprList);
            }
        } finally {
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(),"新增员工："+emp);
            empLogService.insertLog(empLog);
        }

    }

    /**
     * 删除员工信息
     * @param ids 员工ID数组，包含要删除的员工ID
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(Integer[] ids) {

        //删除员工基本信息
        empMapper.deleteById(ids);

        //删除员工工作经历
        empMapper.deleteExprByEmpId(ids);

    }

    /**
     * 根据员工ID查询员工信息
     *
     * @param id 员工ID
     * @return Emp 员工对象，包含员工信息
     */
    @Override
    public EmpResponseByIdDTO findById(Integer id) {
        return empMapper.findById(id);
    }

    /**
     * 根据员工ID更新员工信息
     * @param emp 员工对象，包含员工信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateById(Emp emp) {

        emp.setUpdateTime(LocalDateTime.now());

        empMapper.updateById(emp);

        //先删除工作经历
        empMapper.deleteExprByEmpId(new Integer[]{emp.getId()});

        //保存新的工作经历
        List<EmpExpr> exprList = emp.getExprList();
        if(!exprList.isEmpty()){
            for (EmpExpr expr : exprList) {
                expr.setEmpId(emp.getId());
            }
            empMapper.saveExpr(exprList);
        }

    }
}
