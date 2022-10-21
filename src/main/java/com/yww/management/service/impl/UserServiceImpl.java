package com.yww.management.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yww.management.entity.Menu;
import com.yww.management.entity.User;
import com.yww.management.entity.UserRole;
import com.yww.management.mapper.UserMapper;
import com.yww.management.service.IMenuService;
import com.yww.management.service.IRoleService;
import com.yww.management.service.IUserRoleService;
import com.yww.management.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *      用户信息实体类 服务实现类
 * </p>
 *
 * @Author  yww
 * @Date  2022-10-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    IUserRoleService userRoleService;
    @Autowired
    IRoleService roleService;
    @Autowired
    IMenuService menuService;

    @Override
    public User getByUsername(String username) {
        return this.getOne(new QueryWrapper<User>().lambda().eq(User::getUsername, username));
    }

    @Override
    public List<GrantedAuthority> getUserAuthorities(String userId) {
        // TODO 权限信息可以先从Redis从获取，或者是从Token里面获取
        StringBuilder authority = new StringBuilder();
        // 目前该系统一个用户只对应一个角色信息
        String roleId = userRoleService
                .getOne(new QueryWrapper<UserRole>().lambda().eq(UserRole::getUserId, userId))
                .getRoleId();
        String roleCode = roleService.getById(roleId).getCode();
        if (StrUtil.isNotBlank(roleCode)) {
            authority.append("ROLE_").append(roleCode);
        }
        List<Menu> menus = menuService.getMenusByRoleId(roleId);
        if (menus.size() > 0) {
            for (Menu menu : menus) {
                authority.append(",").append(menu.getCode());
            }
        }
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authority.toString());
    }

}
