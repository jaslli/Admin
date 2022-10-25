package com.yww.management.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * <p>
 *      BigDecimal工具类
 *  用来提供浮点数的精确运算的工具类
 * </p>
 *
 * @ClassName BigDecimalUtil
 * @Author yww
 * @Date 2022/10/25 16:47
 */
public class BigDecimalUtil {

    /**
     *  除法运算默认精度
     */
    private static final int DEFAULT_PRECISION = 10;

    /**
     *  私有化无参构造
     */
    private BigDecimalUtil() {}

    /**
     *  用于浮点数的精确加法
     *
     * @param value1    浮点数1
     * @param value2    浮点数2
     * @return          浮点数1 + 浮点数2
     */
    public static double addUp(double value1, double value2) {
        BigDecimal b1 = BigDecimal.valueOf(value1);
        BigDecimal b2 = BigDecimal.valueOf(value2);
        return b1.add(b2).doubleValue();
    }

    /**
     *  精确减法
     *
     * @param value1    浮点数1
     * @param value2    浮点数2
     * @return          浮点数1 - 浮点数2
     */
    public static double subtract(double value1, double value2) {
        BigDecimal b1 = BigDecimal.valueOf(value1);
        BigDecimal b2 = BigDecimal.valueOf(value2);
        return b1.subtract(b2).doubleValue();
    }

    /**
     *  精确乘法
     *
     * @param value1    浮点数1
     * @param value2    浮点数2
     * @return          浮点数1 * 浮点数2
     */
    public static double multiply(double value1, double value2) {
        BigDecimal b1 = BigDecimal.valueOf(value1);
        BigDecimal b2 = BigDecimal.valueOf(value2);
        return b1.multiply(b2).doubleValue();
    }

    /**
     *  精确除法(使用默认精度)
     *
     * @param value1    浮点数1
     * @param value2    浮点数2
     * @return          浮点数1 / 浮点数2
     */
    public static double div(double value1, double value2) throws IllegalAccessException {
        return div(value1, value2, DEFAULT_PRECISION);
    }

    /**
     *  精确除法(指定精度)
     *  四舍五入
     *
     * @param value1        浮点数1
     * @param value2        浮点数2
     * @param precision     精度
     * @return              浮点数1 / 浮点数2
     */
    public static double div(double value1, double value2, int precision) throws IllegalAccessException {
        if (precision < 0) {
            throw new IllegalAccessException("除法的精度不能小于0");
        }
        return div(value1, value2, precision, RoundingMode.HALF_UP);
    }

    /**
     *  精确除法(指定精度)
     *  指定舍入的策略
     *
     * @param value1        浮点数1
     * @param value2        浮点数2
     * @param precision     精度
     * @param mode          舍入策略
     * @return              浮点数1 / 浮点数2
     */
    public static double div(double value1, double value2, int precision, RoundingMode mode) throws IllegalAccessException {
        if (precision < 0) {
            throw new IllegalAccessException("除法的精度不能小于0");
        }
        BigDecimal b1 = BigDecimal.valueOf(value1);
        BigDecimal b2 = BigDecimal.valueOf(value2);
        return b1.divide(b2, precision, mode).doubleValue();
    }



}
