package com.yang.dynamicDatasourceService.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author yangyang
 * @since 2020-11-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("cau_ent_sys_role")
public class SysRole implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色编号
     */
    private String roleCode;

    private String description;

    /**
     * 1可用，0不可用
     */
    private Integer status;


}
