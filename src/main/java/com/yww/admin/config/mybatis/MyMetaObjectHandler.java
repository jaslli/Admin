package com.yww.admin.config.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.yww.admin.utils.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * <p>
 *      Mybatis-plus的自动填充处理处理
 *      注意是对象属性名不是表的字段名
 * </p>
 *
 * @ClassName MyMetaObjectHandler
 * @Author yww
 * @Date 2022/10/12 21:14
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 插入数据时填充创建和修改时间
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        String username = SecurityUtils.getCurrentUsername();
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        if (null == username) {
            // 匿名账户处理
            this.strictInsertFill(metaObject, "createBy", String.class, "anonymousUser");
            this.strictInsertFill(metaObject, "updateBy", String.class, "anonymousUser");
        } else {
            // 登录账户处理
            this.strictInsertFill(metaObject, "createBy", String.class, username);
            this.strictInsertFill(metaObject, "updateBy", String.class, username);
        }
    }

    /**
     * 修改数据时更新修改时间
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        String username = SecurityUtils.getCurrentUsername();
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        if (null == username) {
            // 匿名账户处理
            this.strictInsertFill(metaObject, "updateBy", String.class, "anonymousUser");
        } else {
            // 登录账户处理
            this.strictInsertFill(metaObject, "updateBy", String.class, username);
        }
    }

}

