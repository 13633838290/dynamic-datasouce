package com.yang.dynamicDatasourceService.enums;

/**
 * @author yangyang
 * @version 1.0.0
 * @ClassName DataSourceEnum
 * @Description 数据源枚举类
 * @createTime 2020/11/6 10:12
 */
public enum DataSourceEnum {
    /**
     * DB1
     */
    DB1("db1"),
    /**
     * DB2
     */
    DB2("db2");

    private String value;

    DataSourceEnum(String value){this.value=value;}

    public String getValue() {
        return value;
    }
}
