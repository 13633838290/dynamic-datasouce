package com.yang.dynamicDatasourceService.service;

import com.yang.dynamicDatasourceService.model.entity.BizItems;

import java.util.List;

/**
 * @author yangyang
 * @version 1.0.0
 * @ClassName DynamicDbService
 * @Description
 * @createTime 2020/11/6 9:45
 */
public interface DynamicDbService {


    /**
     * 测试动态db1
     * @return
     */
    List<BizItems> selectALlBizItems();
}
