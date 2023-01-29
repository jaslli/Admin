package com.yww.admin.security;

import com.yww.admin.common.Result;
import com.yww.admin.utils.ResponseUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *      未登录或登陆过期处理类
 * </p>
 *
 * @ClassName JwtAuthenticationEntryPoint
 * @Author yww
 * @Date 2022/10/15 14:08
 */
@Component
public class TokenAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        ResponseUtil.response(response, Result.failure(403, "未进行登录", authException.getMessage()));
    }

}
