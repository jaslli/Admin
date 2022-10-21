package com.yww.management.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yww.management.system.entity.Menu;
import com.yww.management.system.entity.RoleMenu;
import com.yww.management.system.mapper.MenuMapper;
import com.yww.management.system.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yww.management.system.service.IRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *      菜单权限实体类 服务实现类
 * </p>
 *
 * @Author  yww
 * @Date  2022-10-21
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    IRoleMenuService roleMenuService;

    @Override
    public List<Menu> getMenusByRoleId(String roleId) {
        List<RoleMenu> list = roleMenuService.list(new QueryWrapper<RoleMenu>()
                .lambda().eq(RoleMenu::getRoleId, roleId).select(RoleMenu::getMenuId));
        List<String> menuIds = list.stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
        return this.listByIds(menuIds);
    }
}

