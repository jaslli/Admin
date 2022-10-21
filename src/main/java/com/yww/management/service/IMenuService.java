package com.yww.management.service;

import com.yww.management.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *      菜单权限实体类 服务类
 * </p>
 *
 * @Author yww
 * @Date  2022-10-21
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 根据角色ID查询菜单信息
     *
     * @param roleId    角色ID
     * @return          菜单信息
     */
    List<Menu> getMenusByRoleId(String roleId);

}
