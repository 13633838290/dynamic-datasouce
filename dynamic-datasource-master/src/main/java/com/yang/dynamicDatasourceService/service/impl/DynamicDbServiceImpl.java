package com.yang.dynamicDatasourceService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yang.dynamicDatasourceService.annotation.DS;
import com.yang.dynamicDatasourceService.enums.DataSourceEnum;
import com.yang.dynamicDatasourceService.mapper.open.BizItemsMapper;
import com.yang.dynamicDatasourceService.model.entity.BizItems;
import com.yang.dynamicDatasourceService.service.DynamicDbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yangyang
 * @version 1.0.0
 * @ClassName DynamicDbServiceImpl
 * @Description
 * @createTime 2020/11/6 9:59
 */
@Service
@DS(DataSourceEnum.DB1)
public class DynamicDbServiceImpl implements DynamicDbService {


    @Resource
    private BizItemsMapper bizItemsMapper;

    @Override
    public List<BizItems> selectALlBizItems() {
        return this.bizItemsMapper.selectList(new QueryWrapper<>());
    }
}
