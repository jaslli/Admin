package com.yww.admin.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *      ThreadLocal工具类
 * </p>
 *
 * @ClassName ThreadLocalUtil
 * @Author yww
 * @Date 2022/10/21 14:27
 */
@SuppressWarnings("unchecked")
public class ThreadLocalUtil {

    /**
     * ThreadLocalMap
     */
    private static final ThreadLocal<Map<String, Object>> THREAD_LOCAL_MAP = ThreadLocal.withInitial(HashMap::new);

    /**
     * 私有化无参构造器
     */
    private ThreadLocalUtil() {
    }

    /**
     * 根据指定的key获取当前线程相关的变量值
     *
     * @param key 变量的key
     * @param <T> 变量值的具体类型
     * @return 若无此key对应的变量值, 则返回{@code null}
     * @throws ClassCastException 若接收此返回值的变量类型与上下文保存的值的实际类型不匹配, 则抛出异常
     */
    public static <T> T get(String key) {
        return (T) THREAD_LOCAL_MAP.get().get(key);
    }

    /**
     * 根据指定的key获取当前线程相关的变量值, 若为{@code null}则返回指定的默认值
     *
     * @param key          变量的key
     * @param defaultValue 默认值
     * @param <T>          变量值的具体类型
     * @return 若无此key对应的变量值, 则返回defaultValue
     * @throws ClassCastException 若接收此返回值的变量类型与上下文保存的值的实际类型不匹配, 则抛出异常
     */
    public static <T> T get(String key, T defaultValue) {
        T value = get(key);
        return value == null ? defaultValue : value;
    }

    /**
     * 设置线程相关上下文的变量值
     *
     * @param key   变量的key
     * @param value 变量值
     */
    public static void set(String key, Object value) {
        THREAD_LOCAL_MAP.get().put(key, value);
    }

    /**
     * 删除指定key的变量
     *
     * @param key 变量的key
     */
    public static void remove(String key) {
        THREAD_LOCAL_MAP.get().remove(key);
    }

    /**
     * 清除当前线程相关的上下文
     */
    public static void close() {
        THREAD_LOCAL_MAP.remove();
    }

}
