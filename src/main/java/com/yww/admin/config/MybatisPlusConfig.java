package com.yww.admin.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *      Mybatis-Plus配置类
 * </p>
 *
 * @ClassName MybatisPlusConfig
 * @Author yww
 * @Date 2022/10/12 20:57
 */
@Configuration
@MapperScan("com.yww.admin.system.mapper")
public class MybatisPlusConfig {

    /**
     * 分页插件，并指定MYSQL数据库
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

}