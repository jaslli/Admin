package com.yww.admin.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yww.admin.system.entity.Menu;
import com.yww.admin.system.mapper.MenuMapper;
import com.yww.admin.system.service.IMenuService;
import com.yww.admin.system.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单权限实体类 服务实现类
 * </p>
 *
 * @Author yww
 * @Date 2022-10-21
 */
@Service
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    private final IUserService userService;

    @Override
    public List<Menu> getMenusByRoleId(String roleId) {
        return baseMapper.getMenusByRoleId(roleId);
    }

    @Override
    public List<Menu> getMenus(String userId) {
        String roleId = userService.getRoleIdByUserId(userId);
        return getMenusByRoleId(roleId);
    }

}

