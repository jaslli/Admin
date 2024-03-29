package com.yww.admin.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *      (role_menu)角色菜单权限实体类
 * </p>
 *
 * @Author yww
 * @Date 2022-10-21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("role_menu")
@Schema(name = "RoleMenu", description = "角色菜单权限实体类")
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "数据ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @Schema(description = "角色ID")
    @TableField("role_id")
    private String roleId;

    @Schema(description = "菜单ID")
    @TableField("menu_id")
    private String menuId;

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
