package com.yww.management.security;

import com.yww.management.common.Result;
import com.yww.management.utils.ResponseUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *      无权限或权限不足处理类
 * </p>
 *
 * @ClassName AccessFailureHandler
 * @Author yww
 * @Date 2022/10/15 14:11
 */
@Component
public class AccessFailureHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
        ResponseUtil.response(response, Result.failure(403, "没有权限执行", accessDeniedException.getMessage()));
    }

}
