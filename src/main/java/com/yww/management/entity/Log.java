package com.yww.management.entity;

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
 *    (log)操作日志实体类
 * </p>
 *
 * @Author yww
 * @Date  2022-10-19
 */
@Data
@TableName("log")
@Schema(name = "Log", description = "操作日志实体类")
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "数据ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @Schema(description = "接口名称")
    @TableField("summary")
    private String summary;

    @Schema(description = "用户名称")
    @TableField("username")
    private String username;

    @Schema(description = "开始时间")
    @TableField("start_time")
    private LocalDateTime startTime;

    @Schema(description = "消耗时间")
    @TableField("spend_time")
    private Integer spendTime;

    @Schema(description = "根路径")
    @TableField("basePath")
    private String basePath;

    @Schema(description = "URI")
    @TableField("uri")
    private String uri;

    @Schema(description = "URL")
    @TableField("url")
    private String url;

    @Schema(description = "请求类型")
    @TableField("method")
    private String method;

    @Schema(description = "请求的IP地址")
    @TableField("ip")
    private String ip;

    @Schema(description = "请求的参数")
    @TableField("parameter")
    private String parameter;

    @Schema(description = "返回的结果")
    @TableField("result")
    private String result;

}
