package com.yww.management.security;

import com.yww.management.common.Result;
import com.yww.management.utils.ResponseUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *      认证失败的处理类（没权限访问处理类）
 * </p>
 *
 * @ClassName AccessFailureHandler
 * @Author yww
 * @Date 2022/10/15 14:11
 */
@Component
public class AccessFailureHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        ResponseUtil.response(response, Result.failure(403, "认证失败", accessDeniedException.getMessage()));
    }

}
