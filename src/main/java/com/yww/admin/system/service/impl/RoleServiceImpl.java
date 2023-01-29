package com.yww.admin.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yww.admin.system.entity.Role;
import com.yww.admin.system.mapper.RoleMapper;
import com.yww.admin.system.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * <p>
 *      角色实体类 服务实现类
 * </p>
 *
 * @Author yww
 * @Date 2022-10-21
 */
@Service
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
