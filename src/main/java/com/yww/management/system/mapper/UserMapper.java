package com.yww.management.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yww.management.system.entity.User;

/**
 * <p>
 *      用户信息实体类 Mapper 接口
 * </p>
 *
 * @Author yww
 * @Date  2022-10-21
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户ID获取角色信息
     *
     * @param userId    用户ID
     * @return  角色信息
     */
    //Role getRoleByUserId(@Param("userId") String userId);

}
