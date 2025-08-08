package com.timezero.seckill.aspects;

import com.timezero.seckill.annotations.IgnoreWebLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

/**
 * Http请求日志
 *
 * @author TimeZero
 */
@Aspect
@Component
@Slf4j
public class WebLogAspect {
    @Pointcut("execution(public * com.timezero.seckill.controller..*.*(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        if (!signature.getMethod().isAnnotationPresent(IgnoreWebLog.class)) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            showWebLog(signature, endTime - startTime, attributes);
        }
        return result;
    }

    private void showWebLog(MethodSignature signature, long costTime, ServletRequestAttributes attributes) {
        StringBuilder sb = new StringBuilder();
        sb.append(signature.getDeclaringTypeName()).append(".").append(signature.getName()).append(" ");
        if (!Objects.isNull(attributes)) {
            sb.append(attributes.getRequest().getMethod()).append(":")
                    .append(attributes.getRequest().getRequestURI()).append(" ");
        }
        sb.append(" CostTime:").append(costTime).append("ms");
        log.info(sb.toString());
    }
}
