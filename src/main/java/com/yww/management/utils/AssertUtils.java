package com.yww.management.utils;

import cn.hutool.core.util.StrUtil;
import com.yww.management.common.GlobalException;

/**
 * <p>
 *      断言工具类
 * </p>
 *
 * @ClassName AssertUtils
 * @Author yww
 * @Date 2022/10/20 11:27
 */
public class AssertUtils {

    /**
     * 私有化无参构造器
     */
    private AssertUtils() {}

    /**
     * 直接抛出异常
     *
     * @param message   异常信息
     * @throws GlobalException  全局异常类
     */
    public static void throwException(String message) throws GlobalException {
        throw new GlobalException(message);
    }

    /**
     * 如果对象为{@code null}, 则抛出异常
     *
     * @param object 要判断的对象
     * @throws GlobalException  全局异常类
     */
    public static void notNull(Object object) throws GlobalException {
        notNull(object, "不能处理空对象");
    }

    /**
     * 如果对象为{@code null}, 则抛出异常
     *
     * @param object  要判断的对象
     * @param message 断言失败时的错误信息
     * @throws GlobalException  全局异常类
     */
    public static void notNull(Object object, String message) throws GlobalException {
        if (object == null) {
            throw new GlobalException(message);
        }
    }

    /**
     * 如果字符串为{@code null}、空字符串或仅包含空白字符, 则抛出异常
     *
     * @param text 要进行检查的字符串
     * @throws GlobalException  全局异常类
     */
    public static void hasText(String text) throws GlobalException {
        hasText(text, "此参数不能为空字符串");
    }

    /**
     * 如果字符串为{@code null}、空字符串或仅包含空白字符, 则抛出异常
     *
     * @param text    要进行检查的字符串
     * @param message 断言失败时的错误信息
     * @throws GlobalException  全局异常类
     */
    public static void hasText(String text, String message) throws GlobalException {
        if (StrUtil.isBlank(text)) {
            throw new GlobalException(message);
        }
    }

}
