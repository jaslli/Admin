package com.yww.management.service;

import com.yww.management.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *      用户信息实体类 服务类
 * </p>
 *
 * @Author yww
 * @Date  2022-10-19
 */
public interface IUserService extends IService<User> {

    /**
     * 根据用户名获取用户信息
     *
     * @param username  用户名
     * @return  User    用户信息
     */
    User getByUsername(String username);

}
