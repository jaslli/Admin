package com.yww.management.service.impl;

import com.yww.management.entity.User;
import com.yww.management.mapper.UserMapper;
import com.yww.management.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
