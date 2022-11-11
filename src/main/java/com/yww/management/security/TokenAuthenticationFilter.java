package com.yww.management.security;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yww.management.common.constant.TokenConstant;
import com.yww.management.system.service.IUserService;
import com.yww.management.utils.AssertUtils;
import com.yww.management.utils.ThreadLocalUtil;
import com.yww.management.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

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
 * @Author yww
 * @Date 2022/10/20 15:46
 */
@Slf4j
public class TokenAuthenticationFilter extends BasicAuthenticationFilter {

    @Autowired
    IUserService userService;
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        // 初步检测获取Token
        String token = resolveToken(request);
        if (StrUtil.isNotBlank(token)) {
            // 验证Token
            DecodedJWT decoded = TokenUtil.parse(token);
            // 根据Token里的用户名去获取用户信息
            String username = decoded.getClaim("username").asString();
            log.info(">> checking username: {}   ", username);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // 主要是获取用户信息
                UserDetails userDetail = userDetailsService.loadUserByUsername(username);
                AssertUtils.notNull(userDetail, "用户检测Token异常，请重新登陆！");
                // 填充SecurityContextHolder
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.info(">> authenticated user: {}  ", username);
                // 设置当前用户
                ThreadLocalUtil.set(TokenConstant.ADMIN_TOKEN_CONTEXT, userDetail);
            }
        }
        chain.doFilter(request, response);
    }

    /**
     *  检查请求头是否存在Token，是否以指定前缀开头
     */
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(TokenConstant.TOKEN_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TokenConstant.TOKEN_PREFIX)) {
            // 去掉令牌前缀
            return bearerToken.replace(TokenConstant.TOKEN_PREFIX, "");
        } else {
            log.warn("非法Token：{}", bearerToken);
        }
        return null;
    }

}
