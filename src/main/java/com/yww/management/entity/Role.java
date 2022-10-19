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
 *    (role)角色实体类
 * </p>
 *
 * @Author yww
 * @Date  2022-10-19
 */
@Data
@TableName("role")
@Schema(name = "Role", description = "角色实体类")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "数据ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @Schema(description = "角色编码")
    @TableField("code")
    private String code;

    @Schema(description = "角色名称")
    @TableField("name")
    private String name;

    @Schema(description = "角色描述")
    @TableField("description")
    private String description;

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
