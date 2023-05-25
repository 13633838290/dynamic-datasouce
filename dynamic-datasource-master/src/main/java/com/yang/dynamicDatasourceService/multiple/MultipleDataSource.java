package com.yang.dynamicDatasourceService.multiple;

import com.yang.dynamicDatasourceService.enums.DataSourceEnum;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MultipleDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        if (DataSourceContextHolder.getDataSource() == null) {
            return DataSourceEnum.DB1;
        }
        return DataSourceContextHolder.getDataSource();
    }
}
