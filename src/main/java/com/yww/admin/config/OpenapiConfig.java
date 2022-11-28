package com.yww.admin.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * springdoc-openapi的配置文件
 * </p>
 *
 * @ClassName openapiConfig
 * @Author yww
 * @Date 2022/10/16 6:55
 */
@Configuration
public class OpenapiConfig {

    /**
     * springdoc-openapi的总体配置
     */
    @Bean
    public OpenAPI openApi() {
        return new OpenAPI().info(ywwApiInfo());
    }

    /**
     * RBAC系统接口
     */
    @Bean
    public GroupedOpenApi systemApi() {
        return GroupedOpenApi.builder()
                .group("RBAC的系统接口")
                .packagesToScan("com.yww.management.system.controller")
                .build();
    }

    /**
     * 返回一个自定义的Info
     */
    public Info ywwApiInfo() {
        return new Info()
                // 应用名称
                .title("Yw的管理系统")
                // 应用的描述
                .description("yww的管理系统")
                // 指向服务条款的URL地址
                .termsOfService("https://yww52.com")
                // API的联系人信息
                .contact(new Contact().name("yww").url("https://yww52.com").email("1141950370@qq.com"))
                // API的证书信息
                .license(new License().name("Apache 2.0").url("https://yww52.com"))
                // API版本信息
                .version("1.0");
    }

}
