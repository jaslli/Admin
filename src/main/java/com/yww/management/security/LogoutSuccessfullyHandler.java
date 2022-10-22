package com.yww.management.security;

import com.yww.management.common.Result;
import com.yww.management.utils.ResponseUtil;
import com.yww.management.utils.ThreadLocalUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException {
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        // TODO 清空redis中的token信息
        // 清空ThreadLocal的内容
        ThreadLocalUtil.close();
        ResponseUtil.response(response, Result.success("成功退出登录！"));
    }

}
