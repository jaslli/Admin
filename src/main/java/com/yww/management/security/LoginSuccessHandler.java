package com.yww.management.security;

import com.yww.management.common.Result;
import com.yww.management.common.constant.TokenConstant;
import com.yww.management.system.service.IUserService;
import com.yww.management.utils.ResponseUtil;
import com.yww.management.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 *      登录成功处理器
 *  根据用户名(username)生成Token并返回
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
        String username = authentication.getName();
        String token = TokenUtil.createToken(username);
        List<GrantedAuthority> authorities = userService.getUserAuthorities(username);
        token += TokenConstant.TOKEN_PREFIX;
        ResponseUtil.response(response ,Result.success(token));
    }

}
