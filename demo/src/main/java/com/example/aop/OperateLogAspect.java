// OperateLogAspect.java
package com.example.aop;

import com.example.mapper.OperateLogMapper;
import com.example.pojo.OperateLog;
import com.example.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;

@Slf4j
@Aspect
@Component
public class OperateLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.example.anno.LogOperation)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 创建日志对象
        OperateLog log = new OperateLog();
        
        long startTime = System.currentTimeMillis();

        try {
            // 执行原始方法
            Object result = joinPoint.proceed();
            
            // 设置返回值
            log.setReturnValue(result != null ? result.toString() : "");
            
            return result;
        } catch (Exception e) {
            log.setReturnValue("Exception: " + e.getMessage());
            throw e;
        } finally {
            long endTime = System.currentTimeMillis();
            long costTime = endTime - startTime;

            // 填充其他字段
            log.setOperateEmpId(getCurrentUserId()); // 需要从SecurityContext或其他地方获取当前用户ID
            log.setOperateTime(LocalDateTime.now());
            log.setClassName(joinPoint.getTarget().getClass().getName());
            log.setMethodName(joinPoint.getSignature().getName());
            log.setMethodParams(parseArgs(joinPoint.getArgs()));
            log.setCostTime(costTime);

            // 插入数据库
            operateLogMapper.insert(log);
        }
    }

    /**
     * 从JWT token中获取当前用户的ID
     */
    private Integer getCurrentUserId() {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                String token = request.getHeader("token");
                if (token != null && !token.isEmpty()) {
                    // 解析token获取用户信息
                    io.jsonwebtoken.Claims claims = JwtUtil.parseToken(token);
                    Object userId = claims.get("id");
                    if (userId != null) {
                        return Integer.valueOf(userId.toString());
                    }
                }
            }
        } catch (Exception e) {
            log.error("解析token获取用户ID失败", e);
        }
        return null; // 如果无法获取用户ID，返回null
    }

    /**
     * 解析并格式化方法参数
     */
    private String parseArgs(Object[] args) {
        StringBuilder sb = new StringBuilder();
        for (Object arg : args) {
            if (arg != null) {
                sb.append(arg.toString()).append(",");
            }
        }
        if (!sb.isEmpty()) {
            sb.deleteCharAt(sb.length() - 1); // 删除最后一个逗号
        }
        return sb.toString();
    }
}
