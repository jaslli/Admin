package com.yww.admin.security;

import com.yww.admin.common.Result;
import com.yww.admin.common.constant.TokenConstant;
import com.yww.admin.utils.ResponseUtil;
import com.yww.admin.utils.ThreadLocalUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *      退出登陆成功处理类
 * </p>
 *
 * @ClassName LogoutSuccessfullyHandler
 * @Author yww
 * @Date 2022/10/15 14:12
 */
@Component
public class LogoutSuccessfullyHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        // TODO 清空redis中的token信息
        ThreadLocalUtil.remove(TokenConstant.ADMIN_TOKEN_CONTEXT);
        ResponseUtil.response(response, Result.success("成功退出登录！"));
    }

}
