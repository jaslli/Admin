package com.yww.management.security;

import com.yww.management.utils.ResponseUtil;
import com.yww.management.common.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *      登录失败的处理类
 * </p>
 *
 * @ClassName LoginFailureHandler
 * @Author chenhao
 * @Date 2022/10/15 12:06
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception)
            throws IOException {
        ResponseUtil.response(response, Result.failure(401,"登录失败",exception.getMessage()));
    }

}
