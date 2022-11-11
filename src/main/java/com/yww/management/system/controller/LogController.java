package com.yww.management.system.controller;

import com.yww.management.common.Result;
import com.yww.management.system.entity.Log;
import com.yww.management.system.service.impl.LogServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *      操作日志实体类 前端控制器
 * </p>
 *
 * @Author  yww
 * @Date  2022-10-21
 */
@Tag(name = "操作日志实体类接口")
@RestController
@RequestMapping("/log")
public class LogController {
    private final LogServiceImpl service;

    public LogController(LogServiceImpl service) {
        this.service = service;
    }

    @Operation(summary = "通过ID查询日志", description = "根据数据ID来查询对应的日志数据")
    @GetMapping("/getById")
    public Result<Log> getById(@RequestParam("id") String id) {
        return Result.success(service.getById(id));
    }

}
