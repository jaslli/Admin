package com.yww.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yww.management.entity.User;
import com.yww.management.mapper.UserMapper;
import com.yww.management.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *      用户信息实体类 服务实现类
 * </p>
 *
 * @Author  yww
 * @Date  2022-10-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User getByUsername(String username) {
        return this.getOne(new QueryWrapper<User>().lambda().eq(User::getUsername, username));
    }

}
