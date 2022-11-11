package com.yww.management.security;

import com.yww.management.common.Result;
import com.yww.management.utils.ResponseUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *      登录失败的处理类
 * </p>
 *
 * @ClassName LoginFailureHandler
 * @Author yww
 * @Date 2022/10/15 12:06
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        ResponseUtil.response(response, Result.failure(401,"登录失败",exception.getMessage()));
    }

}
