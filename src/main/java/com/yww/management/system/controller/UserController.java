package com.yww.management.system.controller;

import com.yww.management.common.Result;
import com.yww.management.system.entity.User;
import com.yww.management.system.service.IUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *      用户信息实体类 前端控制器
 * </p>
 *
 * @Author  yww
 * @Date  2022-10-21
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

    @PostMapping("/add")
    public Result<String> add(@RequestBody User user) {
        System.out.println(user);
        service.save(user);
        return Result.success();
    }

}
