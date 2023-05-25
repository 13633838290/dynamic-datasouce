package com.yang.dynamicDatasourceService.annotation;


import com.yang.dynamicDatasourceService.enums.DataSourceEnum;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DS {

    DataSourceEnum value() default DataSourceEnum.DB1;
}
