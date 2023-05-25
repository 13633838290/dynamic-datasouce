package com.yang.dynamicDatasourceService.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class BaseEntity implements Serializable {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("created_by")
    private Long createdBy;

    @TableField("created_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createdTime;

    @TableField("updated_by")
    private Long updatedBy;

    @TableField("updated_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp updatedTime;

    /**
     * 初始化公共字段的默认值
     * eg: BaseEntity 实体的所有字段
     */
    public void initBaseDefault() {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        setCreatedBy(0L);
        setCreatedTime(now);
        setUpdatedBy(0L);
        setUpdatedTime(now);
    }
    /**
     * 初始化公共字段的默认值
     * eg: BaseEntity 实体的所有字段
     */
    public void initBaseDefault(String userInfoId) {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        setCreatedBy(Integer.valueOf(userInfoId).longValue());
        setCreatedTime(now);
        setUpdatedBy(Integer.valueOf(userInfoId).longValue());
        setUpdatedTime(now);
    }
}
