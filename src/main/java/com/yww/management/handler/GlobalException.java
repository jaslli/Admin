package com.yww.management.handler;

import com.yww.management.utils.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.yww.management.utils.ResultCode.FAILED;

/**
 * <p>
 *      自定义异常类
 * </p>
 *
 * @ClassName GlobalException
 * @Author yww
 * @Date 2022/10/12 21:07
 */
@Getter
@AllArgsConstructor
public class GlobalException extends RuntimeException {

    /**
     * 错误码
     */
    private Integer code = FAILED.getStatus();

    /**
     * 错误信息
     */
    private final String message;

    public GlobalException(String message) {
        this.message = message;
    }

    public GlobalException(ResultCode resultCode) {
        this.code = resultCode.getStatus();
        this.message = resultCode.getMessage();
    }

}