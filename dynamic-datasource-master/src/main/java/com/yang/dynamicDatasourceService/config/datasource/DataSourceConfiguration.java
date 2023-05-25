package com.yang.dynamicDatasourceService.config.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.yang.dynamicDatasourceService.enums.DataSourceEnum;
import com.yang.dynamicDatasourceService.multiple.MultipleDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yangyang
 * @version 1.0.0
 * @ClassName DataSourceConfiguration
 * @Description 数据源配置
 * @createTime 2020/11/6 10:11
 */
@Configuration
public class DataSourceConfiguration {

    @Autowired
    private PaginationInterceptor paginationInterceptor;

    @Bean(name = "db1")
    @ConfigurationProperties(prefix = "spring.datasource.druid.db1" )
    public DataSource yltDb() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "db2")
    @ConfigurationProperties(prefix = "spring.datasource.druid.db2" )
    public DataSource wzsDb() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 动态数据源配置
     * @return
     */
    @Bean
    @Primary
    public DataSource multipleDataSource(@Qualifier("db1") DataSource db1, @Qualifier("db2") DataSource db2) {
        MultipleDataSource multipleDataSource = new MultipleDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceEnum.DB1.getValue(), db1);
        targetDataSources.put(DataSourceEnum.DB2.getValue(), db2);
        //添加数据源
        multipleDataSource.setTargetDataSources(targetDataSources);
        //设置默认数据源
        multipleDataSource.setDefaultTargetDataSource(db1);
        return multipleDataSource;
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(multipleDataSource(yltDb(),wzsDb()));
        //sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/*/*Mapper.xml"));

        MybatisConfiguration configuration = new MybatisConfiguration();
        //configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        sqlSessionFactory.setConfiguration(configuration);

        //注入 MyBatis-Plus 分页插件
        Interceptor[] plugins = {paginationInterceptor};
        sqlSessionFactory.setPlugins(plugins);
        return sqlSessionFactory.getObject();
    }


    // *************************************************
    // **********     使用pageHelper 分页     **********
    // *************************************************

    /**
     * pom引入：
     *         <dependency>
     *             <groupId>com.github.pagehelper</groupId>
     *             <artifactId>pagehelper</artifactId>
     *             <version>4.1.3</version>
     *         </dependency>
     */
    /*@Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {

        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(multipleDataSource(signboxDb(), causignDb()));
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        //分页插件
        PageHelper interceptor = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("helperDialect", "mysql");
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("params", "count=countSql");
        properties.setProperty("autoRuntimeDialect", "true");

        interceptor.setProperties(properties);
        //注入 MyBatis-Plus 分页插件
        Interceptor[] plugins = {interceptor};
        fb.setPlugins(plugins);
        //添加XML目录
        try {
            fb.setMapperLocations(resolver.getResources("classpath:mappings/*.xml"));
            return fb.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }*/
}
