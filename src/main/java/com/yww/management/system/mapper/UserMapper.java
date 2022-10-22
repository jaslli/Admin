package com.yww.management.system.mapper;

import com.yww.management.system.entity.Role;
import com.yww.management.system.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.data.repository.query.Param;

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
