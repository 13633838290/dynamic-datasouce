package com.yang.dynamicDatasourceService.aop;


import com.yang.dynamicDatasourceService.annotation.DS;
import com.yang.dynamicDatasourceService.multiple.DataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author yangyang
 * @version 1.0.0
 * @ClassName DataSourceAspect
 * @Description 数据源aop
 * @createTime 2020/11/6 10:12
 */
@Component
@Slf4j
@Aspect
@Order(-1)
public class DataSourceAspect {

    @Around("execution(* com.yang.dynamicDatasourceService.service..*.*(..)) " +
            "&& (@annotation(com.yang.dynamicDatasourceService.annotation.DS) || @within(com.yang.dynamicDatasourceService.annotation.DS))" )
    public Object aroundMethod(ProceedingJoinPoint pjd) throws Throwable {

        Method method = ((MethodSignature)pjd.getSignature()).getMethod();
        // 获取方法上的注解，上面参数的 ds 其实已经注入了，只是这样写不用写过长的包名
        DS ds = method.getAnnotation(DS.class);
        if(ds == null){
            // 获取类上面的注解
            ds = pjd.getTarget().getClass().getAnnotation(DS.class);
        }

        if (ds == null) {
            return pjd.proceed();
        }

        DataSourceContextHolder.setDataSource(ds.value().getValue());
        log.debug("动态数据源, Class: {}, Method: {}, DataSource: {}", pjd.getTarget().getClass().getName(), method.getName(), DataSourceContextHolder.getDataSource());

        try {
            return pjd.proceed();
        } finally {
            DataSourceContextHolder.clear();
        }

    }
}
