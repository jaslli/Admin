package com.yww.admin.security;

import com.yww.admin.common.Result;
import com.yww.admin.common.constant.TokenConstant;
import com.yww.admin.system.service.IUserService;
import com.yww.admin.utils.ResponseUtil;
import com.yww.admin.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 登录成功处理器
 * 根据用户名(username)生成Token并返回
 * </p>
 *
 * @ClassName LoginSuccessHandler
 * @Author yww
 * @Date 2022/10/18 23:13
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

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
        ResponseUtil.response(response, Result.success(token));
    }

}
