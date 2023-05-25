package com.yang.dynamicDatasourceService.service;

import com.yang.dynamicDatasourceService.model.entity.BizItems;
import com.yang.dynamicDatasourceService.model.entity.SysRole;

import java.util.List;

/**
 * @author yangyang
 * @version 1.0.0
 * @ClassName DynamicDb2Serrvice
 * @Description
 * @createTime 2020/11/17 17:35
 */
public interface DynamicDb2Service {


    /**
     * 测试动态db2
     * @return
     */
    List<SysRole> selectAllSysRole();

    /**
     * 测试动态db1
     * @return
     */
    List<BizItems> selectALlBizItems();
}
