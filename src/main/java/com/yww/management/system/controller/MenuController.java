package com.yww.management.system.controller;

import com.yww.management.common.Result;
import com.yww.management.system.entity.Log;
import com.yww.management.system.entity.Menu;
import com.yww.management.system.service.impl.LogServiceImpl;
import com.yww.management.system.service.impl.MenuServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *      菜单权限实体类 前端控制器
 * </p>
 *
 * @Author  yww
 * @Date  2022-10-21
 */
@Tag(name = "菜单权限实体类接口")
@RestController
@RequestMapping("/menu")
public class MenuController {

    private final MenuServiceImpl service;

    public MenuController(MenuServiceImpl service) {
        this.service = service;
    }

    @Operation(summary = "通过用户ID查询权限信息", description = "根据用户ID来查询对应的权限或菜单数据")
    @GetMapping("/getMenusByuserId")
    public Result<List<Menu>> getById(@RequestParam("userId") String userId) {
        return Result.success(service.getMenusByUserId(userId));
    }

}
