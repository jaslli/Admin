package com.yww.management.common.exception;

import com.yww.management.common.exception.GlobalException;
import com.yww.management.common.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
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
     * 处理自定义的服务异常信息
     * 统一处理GlobalException异常，异常处理顺序是从小异常到大异常。
     *
     * @param e 服务异常
     * @return 异常信息
     */
    @ResponseBody
    @ExceptionHandler(value = GlobalException.class)
    public <T> Result<T> errorHandler(GlobalException e) {
        return Result.failure(500, e.getMessage());
    }

    /**
     * 异常信息
     *
     * @param e 服务异常
     * @return 异常信息
     */
    @ExceptionHandler(value = Exception.class)
    public <T> Result<T> errorHandler(Exception e) {
        return Result.failure(e.getMessage());
    }

}
