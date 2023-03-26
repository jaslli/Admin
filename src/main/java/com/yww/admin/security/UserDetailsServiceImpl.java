package com.yww.admin.security;

import com.yww.admin.system.entity.User;
import com.yww.admin.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *      重写SpringSecurity的登录方法
 * </p>
 *
 * @ClassName UserDetailsServiceImpl
 * @Author yww
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
     * 通过用户名查找用户信息
     *
     * @param username 用户名
     * @return UserDetails     用户信息
     * @throws UsernameNotFoundException 找不到对应用户名的用户
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户
        User user = userService.getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("查询不到该用户！！！");
        }
        // 获取用户权限
        String authority = userService.getUserAuthorities(username);
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authority);
        return AccountUser.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(authorities)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(user.getStatus())
                .build();
    }

}
