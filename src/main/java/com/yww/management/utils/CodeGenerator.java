package com.yww.management.utils;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.fill.Column;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;


/**
 * <p>
 *      MyBatisPlus的代码生成器
 * </p>
 *
 * @ClassName CodeGenerator
 * @Author yww
 * @Date 2022/10/8 16:26
 */
public class CodeGenerator {

    /**
     * 获取配置文件application.yml中关于mysql的配置
     * @return  数据库配置
     */
    @NotNull
    @Contract(" -> new")
    private static DataSourceConfig.Builder getDataSourceConfig() {
        // 配置文件为application.yml使用
        YamlPropertiesFactoryBean factoryBean = new YamlPropertiesFactoryBean();
        factoryBean.setResources(new ClassPathResource("application.yml"));
        Properties properties = factoryBean.getObject();
        if (properties == null) {
            throw new RuntimeException("请确认配置文件是否名为application.yml，是否在resources资源目录下！");
        }
        String url = (String) properties.get("spring.datasource.druid.url");
        String username = (String) properties.get("spring.datasource.druid.username");
        String password = (String) properties.get("spring.datasource.druid.password");
        if (url == null || url.isEmpty()) {
            throw new RuntimeException("mysql的url出错！");
        }
        if (username == null || username.isEmpty()) {
            throw new RuntimeException("mysql的username出错！");
        }
        if (password == null || password.isEmpty()) {
            throw new RuntimeException("mysql的password出错！");
        }
        return new DataSourceConfig.Builder(url, username, password);
    }

    /**
     * 执行 run
     */
    public static void main(String[] args)  {
        // 项目路径 + /src/main
        String path = System.getProperty("user.dir").replaceAll("\\\\","/") + "/src/main";
        FastAutoGenerator.create(getDataSourceConfig())
            // 全局配置
            .globalConfig(builder -> builder
                    // 作者名称
                    .author("yww")
                    // 指定输出目录
                    .outputDir(path + "/java")
                    // 禁止打开输出目录
                    .disableOpenDir()
                    // 时间策略
                    .dateType(DateType.TIME_PACK)
                    // 注释日期
                    .commentDate("yyyy-MM-dd")
                    // 开启springdoc的注解
                    .enableSpringdoc()
                    .build()
            )
            // 包配置
            .packageConfig(builder -> builder
                    // 父包名
                    .parent("com.yww.management")
                    .service("service")
                    .build()
            )
            // 模板配置(使用默认模板就注释掉这一段)
            .templateConfig(builder -> builder
                    // 禁用所有模板
                    .disable()
                    // 模板路径配置
                    .entity("/templates/entity.java")
                    .service("/templates/service.java")
                    .serviceImpl("/templates/serviceImpl.java")
                    .mapper("/templates/mapper.java")
                    .xml("/templates/mapper.xml")
                    .controller("/templates/controller.java")
                    .build()
            )
            // 策略配置
            .strategyConfig((scanner, builder) -> builder
                    // 增加表匹配
                    .addInclude(getTables(scanner.apply("请输入表名，如需输入多个表名，可用英文逗号分隔，选择所有表输入all")))
                    // controller策略
                    .controllerBuilder().enableRestStyle().enableHyphenStyle()
                    // entity策略配置
                    .entityBuilder().enableLombok()
                    .idType(IdType.ASSIGN_ID)
                    .enableTableFieldAnnotation()
                    .addTableFills(new Column("create_time", FieldFill.INSERT))
                    .addTableFills(new Column("create_by", FieldFill.INSERT))
                    .addTableFills(new Column("update_time", FieldFill.INSERT_UPDATE))
                    .addTableFills(new Column("update_by", FieldFill.INSERT_UPDATE))
                    .build()
            )
            .execute();
    }

    /**
     * 处理输入all的情况
     */
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }

}