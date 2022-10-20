package com.yww.management.security;

import com.yww.management.entity.User;
import com.yww.management.service.IUserService;
import com.yww.management.utils.AssertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * <p>
 *      重写SpringSecurity的登录方法
 * </p>
 *
 * @ClassName UserDetailsServiceImpl
 * @Author chenhao
 * @Date 2022/10/20 11:32
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserService userService;
    @Autowired
    public UserDetailsServiceImpl(IUserService userService) {
        this.userService = userService;
    }

    /**
     * 登录验证
     *
     * @param username  用户名
     * @return  UserDetails     用户信息
     * @throws UsernameNotFoundException    用户名找不到异常
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户
        User user = userService.getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("查询不到该用户！！！");
        }
        return AccountUser.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                //.authorities(userService.getUserAuthorityInfo(user.getId()))
                .enabled(user.getStatus())
                .build();
    }

}
