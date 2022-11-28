package com.yww.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>
 * web请求配置
 * </p>
 *
 * @ClassName WebConfig
 * @Author yww
 * @Date 2022/10/15 11:38
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 重写跨域过滤器，实现全局跨域
     */
    @Bean
    public CorsFilter corsFilter() {
        // 1. 添加 CORS配置信息
        CorsConfiguration config = new CorsConfiguration();
        //  放行全部原始域
        config.addAllowedOrigin("*");
        //  允许跨越发送cookie
        config.setAllowCredentials(true);
        //  放行哪些请求方式
        config.addAllowedMethod("*");
        //  放行全部原始头信息
        config.addAllowedHeader("*");
        //  放行所有头信息
        config.addExposedHeader("*");
        // 2. 添加映射路径
        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**", config);
        // 3. 返回新的CorsFilter
        return new CorsFilter(corsConfigurationSource);
    }

}
