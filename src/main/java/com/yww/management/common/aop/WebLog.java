package com.yww.management.common.aop;

import lombok.*;

/**
 * <p>
 *      Controller层的日志封装类
 * </p>
 *
 * @ClassName WebLog
 * @Author yww
 * @Date 2022/10/15 17:49
 */
@NoArgsConstructor
@Data
public class WebLog {

    /**
     * 操作描述
     */
    private String description;

    /**
     * 操作的接口名称
     */
    private String summary;

    /**
     * 操作用户
     */
    private String username;

    /**
     * 操作时间
     */
    private Long startTime;

    /**
     * 消耗时间
     */
    private Integer spendTime;

    /**
     * 根路径
     */
    private String basePath;

    /**
     * URI
     */
    private String uri;

    /**
     * URL
     */
    private String url;

    /**
     * 请求类型
     */
    private String method;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 请求参数
     */
    private Object parameter;

    /**
     * 请求返回的结果
     */
    private Object result;

}