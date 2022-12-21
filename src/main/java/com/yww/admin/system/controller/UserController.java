package com.yww.admin.system.controller;

import com.yww.admin.common.Result;
import com.yww.admin.system.entity.Menu;
import com.yww.admin.system.entity.User;
import com.yww.admin.system.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 用户信息实体类 前端控制器
 * </p>
 *
 * @Author yww
 * @Date 2022-10-21
 */
@Tag(name = "用户信息实体类接口")
@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserService service;

    @Autowired
    public UserController(IUserService service) {
        this.service = service;
    }

    @Operation(summary = "通过用户ID查询用户信息", description = "根据用户ID来查询对应的用户数据")
    @GetMapping("/getById/{userId}")
    public Result<User> getById(@PathVariable("userId") String userId) {
        return Result.success(service.getById(userId));
    }

}
