package com.yww.management.common.exception;

import com.yww.management.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *      文件上传异常处理
 * </p>
 *
 * @ClassName FileUploadException
 * @Author chenhao
 * @Date 2022/10/31 9:45
 */
@RestControllerAdvice
@Slf4j
public class FileUploadException {

    /**
     *  上传文件过大异常
     *
     * @param e 服务异常
     * @return 异常信息
     */
    @ResponseBody
    @ExceptionHandler(value = MaxUploadSizeExceededException .class)
    public <T> Result<T> handleMaxSizeException(MaxUploadSizeExceededException e, HttpServletRequest request) {
        log.error(">> MaxUploadSizeExceededException: {}, {}", request.getRequestURI(), e.getMessage());
        String errMessage = e.getMessage();
        if (StringUtils.isBlank(errMessage)) {
            errMessage = "服务器繁忙";
        }
        return Result.failure(errMessage);
    }

}
