package com.yww.management.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 *    (user_role)用户角色关系实体类
 * </p>
 *
 * @Author yww
 * @Date  2022-10-19
 */
@Data
@TableName("user_role")
@Schema(name = "UserRole", description = "用户角色关系实体类")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "数据ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @Schema(description = "用户ID")
    @TableField("user_id")
    private String userId;

    @Schema(description = "角色ID")
    @TableField("role_id")
    private String roleId;

    @Schema(description = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @Schema(description = "创建人")
    @TableField("create_by")
    private String createBy;

    @Schema(description = "更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @Schema(description = "创建人")
    @TableField("update_by")
    private String updateBy;

}
