package com.yww.admin.security;

import com.yww.admin.common.Result;
import com.yww.admin.common.constant.TokenConstant;
import com.yww.admin.system.service.IUserService;
import com.yww.admin.utils.RedisUtil;
import com.yww.admin.utils.ResponseUtil;
import com.yww.admin.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *      登录成功处理器
 *      根据用户名(username)生成Token并返回
 * </p>
 *
 * @ClassName LoginSuccessHandler
 * @Author yww
 * @Date 2022/10/18 23:13
 */
@Slf4j
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private RedisUtil redisUtil;
    private final IUserService userService;

    @Autowired
    public LoginSuccessHandler(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 获取用户名
        String username = authentication.getName();
        // 获取用户权限
        String authority = userService.getUserAuthorities(username);
        // 生成Token
        String token = TokenUtil.createToken(username, authority);
        token = TokenConstant.TOKEN_PREFIX + token;
        String userId = userService.getUserIdByUserName(username);
        redisUtil.setStr(token, userId);
        log.info("{}用户登录成功， 当前用户的ID为： {}", username, userId);
        log.info("当前用户的Token为： {}", token);
        ResponseUtil.response(response, Result.success(token));
    }

}
