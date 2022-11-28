package com.yww.admin.system.controller;

import com.yww.admin.system.service.impl.LogServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 操作日志实体类 前端控制器
 * </p>
 *
 * @Author yww
 * @Date 2022-10-21
 */
@Tag(name = "操作日志实体类接口")
@RestController
@RequestMapping("/log")
public class LogController {
    private final LogServiceImpl service;

    public LogController(LogServiceImpl service) {
        this.service = service;
    }

}
