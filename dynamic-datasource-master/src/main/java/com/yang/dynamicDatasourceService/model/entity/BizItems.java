package com.yang.dynamicDatasourceService.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.yang.dynamicDatasourceService.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

/**
 * <p>
 * 平台业务配置表
 * </p>
 *
 * @author yang
 * @since 2020-08-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("biz_items")
public class BizItems extends BaseEntity {

    /**
     * 办件事项名称
     */
    @TableField("biz_name")
    private String bizName;

    /**
     * 业务状态名称
     */
    @TableField("biz_state_name")
    private String bizStateName;

    /**
     * 推送时间
     */
    @TableField("biz_push_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp bizPushTime;

    /**
     * 平台业务ID
     */
    @TableField("biz_code")
    private String bizCode;

    /**
     * 平台业务流水号
     */
    @TableField("biz_serial_number")
    private String bizSerialNumber;

    /**
     * 状态 1-草稿 2-已提交
     */
    @TableField("status")
    private String status;


}
