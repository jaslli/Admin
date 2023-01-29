package com.yww.admin.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yww.admin.system.entity.User;

/**
 * <p>
 *      用户信息实体类 服务类
 * </p>
 *
 * @Author yww
 * @Date 2022-10-21
 */
public interface IUserService extends IService<User> {

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return User    用户信息
     */
    User getByUsername(String username);

    /**
     * 通过用户名获取用户权限信息
     *
     * @param username 用户名
     * @return 用户权限信息
     */
    String getUserAuthorities(String username);

    /**
     * 根据用户名查询角色ID
     *
     * @param userId 用户名
     * @return 角色ID
     */
    String getRoleIdByUserId(String userId);

}
