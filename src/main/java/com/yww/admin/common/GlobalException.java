package com.yww.admin.common;

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
public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = -1574716826948451793L;

    /**
     * 错误码
     */
    private final Integer code;

    /**
     * 错误信息
     */
    private final String message;

    public GlobalException(String message) {
        this.code = 500;
        this.message = message;
    }

    public GlobalException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}