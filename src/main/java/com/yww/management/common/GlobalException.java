package com.yww.management.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

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

    private static final long serialVersionUID = -1574716826948451793L;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private final String message;

    public GlobalException(String message) {
        this.message = message;
    }

}