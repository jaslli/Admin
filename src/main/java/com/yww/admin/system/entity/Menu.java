package com.yww.admin.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *      (menu)菜单权限实体类
 * </p>
 *
 * @Author yww
 * @Date 2022-10-21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("menu")
@Schema(name = "Menu", description = "菜单权限实体类")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "数据ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @Schema(description = "上级数据ID")
    @TableField("pid")
    private String pid;

    @Schema(description = "菜单（权限）名称")
    @TableField("name")
    private String name;

    @Schema(description = "数据类型,0菜单|1按钮(权限)")
    @TableField("type")
    private Boolean type;

    @Schema(description = "菜单显示,0显示|1隐藏")
    @TableField("visible")
    private Boolean visible;

    @Schema(description = "菜单路由地址")
    @TableField("path")
    private String path;

    @Schema(description = "组件名称")
    @TableField("component")
    private String component;

    @Schema(description = "菜单图标")
    @TableField("icon")
    private String icon;

    @Schema(description = "排序字段")
    @TableField("sort")
    private Integer sort;

    @Schema(description = "权限字段")
    @TableField("code")
    private String code;

    @Schema(description = "子菜单")
    @TableField(exist = false)
    private List<Menu> children;

    @Schema(description = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @Schema(description = "创建人")
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private String createBy;

    @Schema(description = "更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @Schema(description = "更新人")
    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

}
