package com.yww.management.handler;

import com.yww.management.utils.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>
 *      全局异常处理
 * </p>
 *
 * @ClassName ControllerAdviceHandler
 * @Author yww
 * @Date 2022/10/12 21:08
 */
@RestControllerAdvice
public class ControllerAdviceHandler {

    /**
     * 处理所有异常信息
     * @param e 服务异常
     * @return 异常信息
     */
    @ExceptionHandler(value = Exception.class)
    public <T> Result<T> errorHandler(Exception e) {
        return Result.failure(e.getMessage());
    }

    /**
     * 处理通用的服务异常信息
     * 统一处理GlobalException异常，异常处理顺序是从小异常到大异常。
     * @param e 服务异常
     * @return 异常信息
     */
    @ExceptionHandler(value = GlobalException.class)
    public <T> Result<T> errorHandler(GlobalException e) {
        return Result.failure(e.getMessage());
    }

}
