package com.yang.dynamicDatasourceService.controller;

import com.yang.dynamicDatasourceService.commons.JsonResponse;
import com.yang.dynamicDatasourceService.service.DynamicDb2Service;
import com.yang.dynamicDatasourceService.service.DynamicDbService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yangyang
 * @version 1.0.0
 * @ClassName DynamicDBController
 * @Description
 * @createTime 2020/11/6 9:44
 */
@RestController
@RequestMapping("dynamic")
public class DynamicDBController {

    @Resource
    private DynamicDbService service;
    @Resource
    private DynamicDb2Service db2Service;

    @GetMapping("db1")
    public JsonResponse dynamicDB1(){

        return JsonResponse.success(service.selectALlBizItems());
    }

    @GetMapping("db2")
    public JsonResponse dynamicDB2(){

        return JsonResponse.success(db2Service.selectAllSysRole());
    }

    @GetMapping("test1")
    public JsonResponse test1(){

        return JsonResponse.success(db2Service.selectALlBizItems());
    }
}
