package com.yww.management.config;

import cn.hutool.extra.spring.SpringUtil;
import com.yww.management.annotation.AnonymousAccess;
import com.yww.management.security.LoginFailureHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;

/**
 * <p>
 *      SpringSecurity核心配置类
 * </p>
 *
 * @ClassName SecurityConfig
 * @Author yww
 * @Date 2022/10/15 12:01
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginFailureHandler loginFailureHandler;

    /**
     * 密码加密工具
     */
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置静态资源的处理，即在resources下的html，css，js文件（暂无用处）
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/resources/**/*.html","/resources/**/*.css", "/resources/**/*.js");
    }

    /**
     * 请求白名单
     */
    private static final String[] URL_WHITELIST = {
        // 登录和注销接口允许匿名访问
        "/login", "/logout",
        // 放行Knife4j的主页和swagger的资源请求
        "/doc.html", "/webjars/**","/v3/**",
        // 放行druid数据源
        "/druid/**",
    };

    /**
     * SpringSecurity的核心配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 允许跨域
        http.cors().and()
                // 关闭csrf
                .csrf().disable()
                // 使用自定义session管理策略，让 SpringSecurity不创建和使用 session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 登录配置
        http.formLogin()
                // 登录页的用户名属性名
                .usernameParameter("username")
                // 登录页的密码属性名
                .passwordParameter("password")
                // 登陆接口请求路径（POST请求）
                .loginProcessingUrl("/login")
                // 登陆成功处理器
                //.successHandler(loginSuccessHandler)
                // 登陆失败处理器
                .failureHandler(loginFailureHandler);
        // 注销配置
        http.logout()
                // 注销登陆接口
                .logoutUrl("/logout");
                // 注销成功处理器
                //.logoutSuccessHandler(authLogoutSuccessHandler);
        // 异常处理器的配置
        http.exceptionHandling();
                // 未登录处理类
                //.authenticationEntryPoint(jwtAuthenticationEntryPoint)
                // 权限不足处理类
                //.accessDeniedHandler(jwtAccessDeniedHandler);
        // 自定义配置过滤器
        //http.addFilter(jwtAuthenticationFilter());
        // 权限路由配置
        http.authorizeRequests()
                // 请求白名单放行
                .antMatchers(URL_WHITELIST).permitAll()
                // 跨域请求会先进行一次options请求
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                // 自定义匿名访问所有URL放行
                .antMatchers(getAnonymousUrl()).anonymous()
                // 除去上面的任何请求全部拦截
                .anyRequest().authenticated();
    }

    /**
     * 获取所有标有注解AnonymousAccess的访问路径
     *
     * @return  需要匿名访问的访问路径
     */
    private String[] getAnonymousUrl() {
        // 获取所有的 RequestMapping
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = SpringUtil.getBean(RequestMappingHandlerMapping.class).getHandlerMethods();
        Set<String> res = new HashSet<>();
        // 循环 RequestMapping
        for (Map.Entry<RequestMappingInfo, HandlerMethod> infoEntry : handlerMethods.entrySet()) {
            HandlerMethod value = infoEntry.getValue();
            // 获取方法上 AnonymousAccess 类型的注解
            AnonymousAccess methodAnnotation = value.getMethodAnnotation(AnonymousAccess.class);
            // 如果方法上标注了 AnonymousAccess 注解，就获取该方法的访问全路径
            if (methodAnnotation != null) {
                if (infoEntry.getKey().getPatternsCondition() != null) {
                    res.addAll(infoEntry.getKey().getPatternsCondition().getPatterns());
                }
            }
        }
        return res.toArray(new String[0]);
    }

}
