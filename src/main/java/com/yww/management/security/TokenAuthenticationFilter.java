package com.yww.management.security;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yww.management.common.constant.TokenConstant;
import com.yww.management.system.service.IUserService;
import com.yww.management.utils.ThreadLocalUtil;
import com.yww.management.utils.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenAuthenticationFilter.class);

    @Autowired
    IUserService userService;
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        // 从请求头部获取Token
        String token = request.getHeader(TokenConstant.TOKEN_HEADER);
        if (StrUtil.isNotBlank(token)) {
            // 验证Token
            DecodedJWT decoded = TokenUtil.parse(token);
            // 根据Token里的用户名去获取用户信息
            String username = decoded.getClaim("username").asString();
            ThreadLocalUtil.set("username",username);
            LOGGER.info("checking username:{}   ", username);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // 主要是获取用户信息
                UserDetails userDetail = userDetailsService.loadUserByUsername(username);
                // 填充SecurityContextHolder
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
                LOGGER.info("authenticated user:{}  ", username);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response);
    }

}
