package com.yang.dynamicDatasourceService.config.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author yangyang
 * @version 1.0.0
 * @ClassName MyBatisConfiguration
 * @Description mybatis 插件配置
 * @createTime 2020/11/6 10:11
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan(basePackages = "com.yang.dynamicDatasourceService.mapper", markerInterface = BaseMapper.class)
public class MyBatisConfiguration {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setDbType(DbType.MYSQL);
        return paginationInterceptor;
    }

}
