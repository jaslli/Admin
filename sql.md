## 用户表
``` mysql
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
    `id` CHAR(19) NOT NULL COMMENT '数据ID',
  	`username` VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
  	`password` VARCHAR(50) NOT NULL COMMENT '密码',
  	`nickname` VARCHAR(50) DEFAULT '用户昵称' COMMENT '用户昵称',
  	`avatar` VARCHAR(50) DEFAULT '' COMMENT '用户头像地址',
  	`email` VARCHAR(50) DEFAULT '123456@qq.com' COMMENT '用户邮箱地址',
  	`status` BIT DEFAULT 1 COMMENT '账号状态,0禁用|1正常',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `create_by` VARCHAR(50) NOT NULL COMMENT '创建人',
    `update_time` datetime NOT NULL COMMENT '更新时间',
    `update_by` VARCHAR(50) NOT NULL COMMENT '创建人',
    PRIMARY KEY (`id`)
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '用户信息实体类';
```

## 用户角色表
``` mysql
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
    `id` CHAR(19) NOT NULL COMMENT '数据ID',
  	`user_id` CHAR(19) NOT NULL COMMENT '用户ID',
  	`role_id` CHAR(19) NOT NULL COMMENT '角色ID',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `create_by` VARCHAR(50) NOT NULL COMMENT '创建人',
    `update_time` datetime NOT NULL COMMENT '更新时间',
    `update_by` VARCHAR(50) NOT NULL COMMENT '创建人',
    PRIMARY KEY (`id`)
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '用户角色关系实体类';
```

## 角色表
``` mysql
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
    `id` CHAR(19) NOT NULL COMMENT '数据ID',
  	`code` CHAR(25) NOT NULL COMMENT '角色编码',
  	`name` VARCHAR(50) NOT NULL COMMENT '角色名称',
  	`description` VARCHAR(50) DEFAULT NULL COMMENT '角色描述',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `create_by` VARCHAR(50) NOT NULL COMMENT '创建人',
    `update_time` datetime NOT NULL COMMENT '更新时间',
    `update_by` VARCHAR(50) NOT NULL COMMENT '创建人',
    PRIMARY KEY (`id`)
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '角色实体类';
```

## 角色菜单表
``` mysql
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
    `id` CHAR(19) NOT NULL COMMENT '数据ID',
  	`role_id` CHAR(19) NOT NULL COMMENT '角色ID',
  	`menu_id` CHAR(50) NOT NULL COMMENT '菜单ID',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `create_by` VARCHAR(50) NOT NULL COMMENT '创建人',
    `update_time` datetime NOT NULL COMMENT '更新时间',
    `update_by` VARCHAR(50) NOT NULL COMMENT '创建人',
    PRIMARY KEY (`id`)
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '角色菜单权限实体类';
```

## 菜单权限表
``` mysql
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
    `id` CHAR(19) NOT NULL COMMENT '数据ID',
  	`pid` CHAR(19) DEFAULT 0 COMMENT '上级数据ID',
    `name` VARCHAR(50) NOT NULL COMMENT '菜单（权限）名称',
    `type` bit DEFAULT 1 COMMENT '数据类型,0菜单|1按钮(权限)',
  	`visible` bit DEFAULT 0 COMMENT '菜单显示,0显示|1隐藏',
  	`path` VARCHAR(50) NOT NULL COMMENT '菜单路由地址',
  	`component` VARCHAR(50) NOT NULL COMMENT '组件名称',
  	`icon` VARCHAR(50) NOT NULL COMMENT '菜单图标',
  	`sort` INT(5) DEFAULT 1 COMMENT '排序字段',
  	`code` VARCHAR(50) DEFAULT NULL COMMENT '权限字段',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `create_by` VARCHAR(50) NOT NULL COMMENT '创建人',
    `update_time` datetime NOT NULL COMMENT '更新时间',
    `update_by` VARCHAR(50) NOT NULL COMMENT '创建人',
    PRIMARY KEY (`id`)
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '菜单权限实体类';
```

## 操作日志

``` mysql
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
    `id` CHAR(19) NOT NULL COMMENT '数据ID',
    `summary` VARCHAR(50) DEFAULT '' COMMENT '接口名称',
  	`username` VARCHAR(50) DEFAULT '' COMMENT '用户名称',
  	`description` VARCHAR(50) DEFAULT '' COMMENT '接口描述',
  	`start_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间',
  	`spend_time` INT DEFAULT 0 COMMENT '消耗时间',
  	`basePath` VARCHAR(50) DEFAULT '' COMMENT '根路径',
    `uri` VARCHAR(50) DEFAULT '' COMMENT 'URI',
    `url` VARCHAR(50) DEFAULT '' COMMENT 'URL',
  	`method` CHAR(10) DEFAULT '' COMMENT '请求类型',
  	`ip` varchar(128) DEFAULT '' COMMENT '请求的IP地址',
  	`parameter` varchar(50) DEFAULT '' COMMENT '请求的参数',
  	`result` varchar(50) DEFAULT '' COMMENT '返回的结果',
    PRIMARY KEY (`id`)
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '操作日志实体类';
```
