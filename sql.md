## 用户表
``` mysql
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
    `id`            CHAR(19)        NOT NULL                        COMMENT '数据ID',
  	`username`      VARCHAR(200)    NOT NULL                        COMMENT '用户名',
  	`password`      VARCHAR(200)    NOT NULL                        COMMENT '密码',
  	`nickname`      VARCHAR(200)    NULL    DEFAULT '用户昵称'       COMMENT '用户昵称',
  	`avatar`        VARCHAR(200)    NULL    DEFAULT ''              COMMENT '用户头像地址',
  	`email`         VARCHAR(200)    NULL    DEFAULT '123456@qq.com' COMMENT '用户邮箱地址',
  	`status`        BIT DEFAULT 1   NOT NULL                        COMMENT '账号状态,0禁用|1正常',
    `create_time`   DATETIME        NOT NULL                        COMMENT '创建时间',
    `create_by`     VARCHAR(200)    NOT NULL                        COMMENT '创建人',
    `update_time`   DATETIME        NOT NULL                        COMMENT '更新时间',
    `update_by`     VARCHAR(200)    NOT NULL                        COMMENT '更新人',
    PRIMARY KEY (`id`),
    CONSTRAINT  username unique (username)
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '用户信息实体类';

INSERT INTO user (id, username, password, nickname, avatar, email, status, create_time, create_by, update_time, update_by) VALUES ('1', 'yww', '$2a$10$orL9cejAoMqoi3cdbJ2G.OQB6GiXwT.wjpxQfnmrhs40eAcEIVQ2G', 'yww', DEFAULT, DEFAULT, true, '2022-10-23 07:32:18', 'yww', '2022-10-23 07:32:26', 'yww')

```

## 用户角色表
``` mysql
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
     `id`           CHAR(19)        NOT NULL COMMENT '数据ID',
     `user_id`      CHAR(19)        NOT NULL COMMENT '用户ID',
     `role_id`      CHAR(19)        NOT NULL COMMENT '角色ID',
     `create_time`  DATETIME        NOT NULL COMMENT '创建时间',
     `create_by`    VARCHAR(200)    NOT NULL COMMENT '创建人',
     `update_time`  DATETIME        NOT NULL COMMENT '更新时间',
     `update_by`    VARCHAR(200)    NOT NULL COMMENT '更新人',
     PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '用户角色关系实体类';

INSERT INTO user_role (id, user_id, role_id, create_time, create_by, update_time, update_by) VALUES ('1', '1', '1', '2022-11-10 16:20:46', 'yww', '2022-11-10 16:21:10', 'yww')

```

## 角色表
``` mysql
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
    `id`            CHAR(19)                NOT NULL    COMMENT '数据ID',
    `code`          CHAR(25)                NOT NULL    COMMENT '角色编码',
    `name`          VARCHAR(200)            NOT NULL    COMMENT '角色名称',
    `description`   VARCHAR(200)    DEFAULT NULL        COMMENT '角色描述',
    `create_time`   DATETIME NOT            NULL        COMMENT '创建时间',
    `create_by`     VARCHAR(200)            NOT NULL    COMMENT '创建人',
    `update_time`   DATETIME NOT            NULL        COMMENT '更新时间',
    `update_by`     VARCHAR(200)            NOT NULL    COMMENT '更新人',
    PRIMARY KEY (`id`),
    CONSTRAINT code unique (code)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '角色实体类';

INSERT INTO role (id, code, name, description, create_time, create_by, update_time, update_by) VALUES ('1', 'admin', 'yww', '超级管理员', '2022-11-10 16:23:25', 'yww', '2022-11-10 16:23:28', 'yww')

```

## 角色菜单表
``` mysql
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
    `id`            CHAR(19)    NOT NULL COMMENT '数据ID',
    `role_id`       CHAR(19)    NOT NULL COMMENT '角色ID',
    `menu_id`       CHAR(50)    NOT NULL COMMENT '菜单ID',
    `create_time`   DATETIME    NOT NULL COMMENT '创建时间',
    `create_by`     VARCHAR(50) NOT NULL COMMENT '创建人',
    `update_time`   DATETIME    NOT NULL COMMENT '更新时间',
    `update_by`     VARCHAR(50) NOT NULL COMMENT '更新人',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '角色菜单权限实体类';

INSERT INTO role_menu (id, role_id, menu_id, create_time, create_by, update_time, update_by) VALUES ('1', '1', '1', '2022-11-10 16:33:15', 'yww', '2022-11-10 16:33:22', 'yww')

```

## 菜单权限表
``` mysql
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
    `id`            CHAR(19)                    NOT NULL    COMMENT '数据ID',
    `pid`           CHAR(19)        DEFAULT 0   NULL        COMMENT '上级数据ID',
    `name`          VARCHAR(100)                NOT NULL    COMMENT '菜单（权限）名称',
    `type`          BIT             DEFAULT 1   NULL        COMMENT '数据类型,0菜单|1按钮(权限)',
    `visible`       BIT             DEFAULT 0   NULL        COMMENT '菜单显示,0显示|1隐藏',
    `path`          VARCHAR(200)                NOT NULL    COMMENT '菜单路由地址',
    `component`     VARCHAR(100)                NOT NULL    COMMENT '组件名称',
    `icon`          VARCHAR(100)                NOT NULL    COMMENT '菜单图标',
    `sort`          INT(5)          DEFAULT 1   NULL        COMMENT '排序字段',
    `code`          VARCHAR(100)    DEFAULT     NULL        COMMENT '权限字段',
    `create_time`   DATETIME                    NOT NULL    COMMENT '创建时间',
    `create_by`     VARCHAR(200)                NOT NULL    COMMENT '创建人',
    `update_time`   DATETIME                    NOT NULL    COMMENT '更新时间',
    `update_by`     VARCHAR(200)                NOT NULL    COMMENT '更新人',
    PRIMARY KEY (`id`),
    CONSTRAINT name unique (name),
    CONSTRAINT path unique (path),
    CONSTRAINT component unique (component),
    CONSTRAINT code unique (code)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '菜单权限实体类';

INSERT INTO menu (id, pid, name, type, visible, path, component, icon, sort, code, create_time, create_by, update_time, update_by) VALUES ('1', '0', '布局', false, false, '/layout', 'Layout', 'test', 9999, 'layout', '2022-11-10 16:35:34', 'yww', '2022-11-10 16:35:38', 'yww')


```

## 操作日志

``` mysql
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
    `id`            CHAR(19) NOT NULL COMMENT '数据ID',
    `summary`       VARCHAR(50) DEFAULT ''                  COMMENT '接口名称',
  	`username`      VARCHAR(50) DEFAULT ''                  COMMENT '操作用户',
  	`description`   VARCHAR(50) DEFAULT ''                  COMMENT '接口描述',
  	`start_time`    DATETIME    DEFAULT CURRENT_TIMESTAMP   COMMENT '开始时间',
  	`spend_time`    INT         DEFAULT 0                   COMMENT '请求耗时',
  	`basePath`      VARCHAR(50) DEFAULT ''                  COMMENT '根路径',
    `uri`           VARCHAR(50) DEFAULT ''                  COMMENT 'URI',
    `url`           VARCHAR(50) DEFAULT ''                  COMMENT 'URL',
    `browser`       VARCHAR(50) DEFAULT ''                  COMMENT '浏览器',
  	`method`        CHAR(10)    DEFAULT ''                  COMMENT '请求类型',
  	`ip`            VARCHAR(128)DEFAULT ''                  COMMENT '请求的IP地址',
  	`parameter`     VARCHAR(50) DEFAULT ''                  COMMENT '请求的参数',
  	`result`        VARCHAR(50) DEFAULT ''                  COMMENT '返回的结果',
    PRIMARY KEY (`id`)
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '操作日志实体类';
```
