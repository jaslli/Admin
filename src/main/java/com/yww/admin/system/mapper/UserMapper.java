package com.yww.admin.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yww.admin.system.entity.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户信息实体类 Mapper 接口
 * </p>
 *
 * @Author yww
 * @Date 2022-10-21
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户ID获取角色ID
     *
     * @param userId 用户名
     * @return 角色ID
     */
    String getRoleIdByUserId(@Param("userId") String userId);

}
