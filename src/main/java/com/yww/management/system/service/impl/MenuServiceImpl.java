package com.yww.management.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yww.management.system.entity.Menu;
import com.yww.management.system.entity.RoleMenu;
import com.yww.management.system.mapper.MenuMapper;
import com.yww.management.system.service.IMenuService;
import lombok.RequiredArgsConstructor;
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

    @Override
    public List<Menu> getMenusByRoleId(String roleId) {
        return baseMapper.getMenusByRoleId(roleId);
    }

}

