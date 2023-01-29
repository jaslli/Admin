package com.yww.admin.utils;

import cn.hutool.core.util.StrUtil;
import com.yww.admin.common.GlobalException;

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
    private AssertUtils() {
    }

    /**
     * 如果对象为{@code null}, 则抛出异常
     *
     * @param object  要判断的对象
     * @param message 断言失败时的错误信息
     * @throws GlobalException 全局异常类
     */
    public static void notNull(Object object, String message) throws GlobalException {
        if (object == null) {
            throw new GlobalException(message);
        }
    }

    /**
     * 如果字符串为{@code null}、空字符串或仅包含空白字符, 则抛出异常
     *
     * @param text    要进行检查的字符串
     * @param message 断言失败时的错误信息
     * @throws GlobalException 全局异常类
     */
    public static void hasText(String text, String message) throws GlobalException {
        if (StrUtil.isBlank(text)) {
            throw new GlobalException(message);
        }
    }

    /**
     * 判断一个布尔表达式, 若表达式为{@code false}则抛出指定错误信息的{@code BusinessException}.
     *
     * @param expression 布尔表达式
     * @param errCode    断言失败时的错误代码
     * @param message    断言失败时的错误信息
     * @throws GlobalException 自定义全局异常
     */
    public static void isTrue(boolean expression, int errCode, String message) throws GlobalException {
        if (!expression) {
            throw new GlobalException(errCode, message);
        }
    }

    /**
     * 判断一个布尔表达式, 若表达式为{@code false}则抛出指定错误信息的{@code GlobalException}.
     *
     * @param expression 布尔表达式
     * @param message    断言失败时的错误信息
     * @throws GlobalException 自定义全局异常
     */
    public static void isTrue(boolean expression, String message) throws GlobalException {
        isTrue(expression, 500, message);
    }

}
