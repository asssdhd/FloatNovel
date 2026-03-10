package org.example.floatnovel.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.floatnovel.annotation.Log;
import org.example.floatnovel.entity.OperationLog;
import org.example.floatnovel.service.OperationLogService;
import org.example.floatnovel.utility.GetUserIp;
import org.example.floatnovel.utility.SaTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;

@Component
@Aspect
public class Gaspect {


    @Autowired
    private OperationLogService  operationLogService;

    @Autowired
    private GetUserIp getUserIp ;

   @Pointcut("@annotation(log)")
   public void logCut(Log log) {};

    /*
    * 记录管理员的操作日志
    * 2026.1.29*/
    @Around("logCut(log)")
    public Object around(ProceedingJoinPoint joinPoint,Log log) throws Throwable {


        //记录开始时间
        long start = System.currentTimeMillis();
        //创建返回结果
        Object result = ' ';


        try {
            //执行原方法
            result=joinPoint.proceed();
            //返回结果
            return  result;
        } finally {
            //记录花费时间
            long costTime = System.currentTimeMillis() - start;
            //记录日志
            saveLog(joinPoint,log,costTime);

        }

    }


    private void saveLog(ProceedingJoinPoint joinPoint,
                         Log log,
                         long costTime) {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        OperationLog logEntity = new OperationLog();
        logEntity.setModule(log.module());
        logEntity.setOperation(log.operation());
        logEntity.setDescription(log.description());

        logEntity.setMethod(
                joinPoint.getTarget().getClass().getName()
                        + "." + method.getName()
        );

        // 参数
        String params = Arrays.toString(joinPoint.getArgs());
        logEntity.setRequestParams(params);

        // 当前用户名（从 ThreadLocal / SecurityContext 取）
        logEntity.setOperator(String.valueOf(SaTokenUtil.getUserId()));

        logEntity.setIp(getUserIp.getClientIp());
        logEntity.setOperateTime(LocalDateTime.now());

        // 建议：异步保存
        operationLogService.save(logEntity);
    }


}
