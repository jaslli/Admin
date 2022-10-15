package com.yww.management.controller;

import com.yww.management.annotation.AnonymousAccess;
import com.yww.management.annotation.Log;
import com.yww.management.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @ClassName TestController
 * @Author chenhao
 * @Date 2022/10/15 11:32
 */
@RestController
public class TestController {

    @Log("执行方法hello()")
    @AnonymousAccess
    @GetMapping("ywwapi/hello")
    public Result<String> hello() {
        return Result.success("hello world");
    }

}
