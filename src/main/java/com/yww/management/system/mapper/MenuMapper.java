package com.yww.management.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yww.management.system.entity.Menu;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *      菜单权限实体类 Mapper 接口
 * </p>
 *
 * @Author yww
 * @Date  2022-10-21
 */
@Repository
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据角色ID获取菜单信息
     *
     * @param roleId    角色ID
     * @return  菜单信息
     */
    List<Menu> getMenusByRoleId(@Param("roleId") String roleId);

}
