package com.yww.management.controller;

import com.yww.management.annotation.AnonymousAccess;
import com.yww.management.annotation.Log;
import com.yww.management.common.Result;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @ClassName TestController
 * @Author yww
 * @Date 2022/10/15 11:32
 */
@RestController
public class TestController {

    @Log()
    @AnonymousAccess
    @Operation(summary = "hello1", description = "hello1")
    @GetMapping("ywwapi/hello1")
    public Result<String> hello() {
        return Result.success("hello world");
    }

}
