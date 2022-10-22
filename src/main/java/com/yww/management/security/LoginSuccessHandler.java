package com.yww.management.security;

import com.yww.management.common.Result;
import com.yww.management.utils.ResponseUtil;
import com.yww.management.utils.TokenUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

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

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String token = TokenUtil.genToken(new HashMap<String, Object>(1) {
            private static final long serialVersionUID = 1L;
            {
                put("username", authentication.getName());
            }
        });
        ResponseUtil.response(response ,Result.success(token));
    }


}
