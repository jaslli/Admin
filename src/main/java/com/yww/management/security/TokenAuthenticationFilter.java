package com.yww.management.security;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yww.management.entity.User;
import com.yww.management.service.IUserService;
import com.yww.management.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *      Token身份验证过滤器
 * </p>
 *
 * @ClassName TokenAuthenticationFilter
 * @Author chenhao
 * @Date 2022/10/20 15:46
 */
public class TokenAuthenticationFilter extends BasicAuthenticationFilter {

    private final String TOKENHEADER = "test";

    @Autowired
    IUserService userService;

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        // 从请求头部获取Token
        String token = request.getHeader(TOKENHEADER);
        // 没有Token就放行给下一个过滤器
        if (StrUtil.isBlankOrUndefined(token)) {
            chain.doFilter(request, response);
            return;
        }
        // 验证Token
        DecodedJWT decoded = TokenUtil.parse(token);
        // 根据Token里的用户名去获取用户信息
        String username = decoded.getClaim("username").asString();
        User user = userService.getByUsername(username);
        // 填充SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(
                    username,
                    null,
//                        userService.getUserAuthorityInfo(user.getId())
                    null
            );
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

}
