package com.yww.management.utils;

import cn.hutool.extra.spring.SpringUtil;
import com.yww.management.common.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

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
     * 获取当前登录的用户
     *
     * @return UserDetails
     */
    public static UserDetails getCurrentUser() {
        String username = getCurrentUsername();
        // 匿名用户
        if (username == null) {
            return null;
        }
        UserDetailsService userDetailsService = SpringUtil.getBean(UserDetailsService.class);
        return userDetailsService.loadUserByUsername(username);
    }

    /**
     * 获取当前登陆用户的用户名
     *
     * @return 用户名
     */
    public static String getCurrentUsername() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new GlobalException(503, "当前登录状态过期");
        }
        // 判断是否是匿名用户
        if (ANONYMOUS_USER.equals(authentication.getPrincipal())) {
            return null;
        }
        // 获取用户名
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userDetails.getUsername();
        }
        throw new GlobalException(503, "当前登录状态异常");
    }

}
