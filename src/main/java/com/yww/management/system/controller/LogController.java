package com.yww.management.system.controller;

import com.yww.management.common.Result;
import com.yww.management.system.entity.Log;
import com.yww.management.system.service.impl.LogServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequiredArgsConstructor
@RestController
@RequestMapping("/log")
public class LogController {
    private final LogServiceImpl service;

    @Operation(summary = "通过ID查询日志", description = "根据数据ID来查询对应的日志数据")
    @GetMapping("/getById/{id}")
    public Result<Log> getById(@Parameter(name = "日志的数据ID") @PathVariable("id") String id) {
        return Result.success(service.getById(id));
    }

}
