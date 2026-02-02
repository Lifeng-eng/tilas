//package com.example.aop;
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Aspect
//@Component
//public class RecordTimeApsect {
//
//    /**
//     * 环绕通知，统计service.impl包下所有方法的执行耗时
//     *
//     * @param joinPoint 连接点
//     * @return 方法执行结果
//     * @throws Throwable 可能抛出的异常
//     */
//    @Around("execution(* com.example.service.*Service.*(..))")
//
//    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
//        // 获取方法名
//        String methodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
//
//        // 记录开始时间
//        long startTime = System.currentTimeMillis();
//
//        try {
//            // 执行目标方法
//            Object result = joinPoint.proceed();
//
//            // 记录结束时间
//            long endTime = System.currentTimeMillis();
//
//            // 计算执行耗时
//            long costTime = endTime - startTime;
//
//            // 记录耗时日志
//            log.info("方法 {} 执行耗时: {} ms", methodName, costTime);
//
//            return result;
//        } catch (Throwable throwable) {
//            // 记录结束时间
//            long endTime = System.currentTimeMillis();
//
//            // 计算执行耗时
//            long costTime = endTime - startTime;
//
//            // 记录异常情况下的耗时日志
//            log.error("方法 {} 执行异常，耗时: {} ms", methodName, costTime);
//
//            // 重新抛出异常
//            throw throwable;
//        }
//    }
//}
