package com.yww.admin.security;

import com.yww.admin.common.Result;
import com.yww.admin.common.constant.TokenConstant;
import com.yww.admin.utils.RedisUtil;
import com.yww.admin.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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
@Slf4j
@Component
public class LogoutSuccessfullyHandler implements LogoutSuccessHandler {

    @Resource
    private RedisUtil redisUtil;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String token = request.getHeader(TokenConstant.TOKEN_HEADER);
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        log.info("用户退出登录成功， 用户的ID为： {}", redisUtil.get(token));
        log.info("用户的Token为： {}", token);
        redisUtil.deleteStr(token);
        ResponseUtil.response(response, Result.success("成功退出登录！"));
    }

}
