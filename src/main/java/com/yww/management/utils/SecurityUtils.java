package com.yww.management.utils;

import com.yww.management.security.AccountUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * <p>
 *      当前登陆用户工具类
 * </p>
 *
 * @ClassName SecurityUtils
 * @Author yww
 * @Date 2022/11/11 14:26
 */
@Slf4j
public class SecurityUtils {

    private static final String ANONYMOUS_USER = "anonymousUser";

    /**
     * 获取当前用户的用户信息
     *
     * @return  /
     */
    public static AccountUser getCurrentUser() {
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // 如果是匿名账号，返回空值
        if (ANONYMOUS_USER.equals(object)) {
            return null;
        } else {
            // 返回用户信息
            return (AccountUser) object;
        }
    }

}
