package com.yww.management.service;

import com.yww.management.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * <p>
 *      用户信息实体类 服务类
 * </p>
 *
 * @Author yww
 * @Date  2022-10-21
 */
public interface IUserService extends IService<User> {

    /**
     * 根据用户名获取用户信息
     *
     * @param username  用户名
     * @return  User    用户信息
     */
    User getByUsername(String username);

    /**
     * 通过用户ID获取用户权限信息
     *
     * @param userId    用户ID
     * @return          用户权限信息
     */
    List<GrantedAuthority> getUserAuthorities(String userId);

}
