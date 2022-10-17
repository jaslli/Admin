package com.yww.management.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *      springdoc-openapi的配置文件
 * </p>
 *
 * @ClassName openapiConfig
 * @Author yww
 * @Date 2022/10/16 6:55
 */
@Configuration
public class OpenapiConfig {

    @Bean
    public GroupedOpenApi ywwApi() {
        return GroupedOpenApi.builder()
                .group("yw的管理系统")
                .pathsToMatch("/**")
                .build();
    }

}
