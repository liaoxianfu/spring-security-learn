package com.liao.security.learn04.security.service;

import com.liao.security.learn04.domain.WebUserDetails;
import com.liao.security.learn04.mapper.UserMapper;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liao
 * @since 2020/9/25 17:09
 */
@Component
public class WebUserDetailService implements UserDetailsService {
    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
// 加载基础信息
        WebUserDetails userDetails = userMapper.getUserDetailsByUsername(username);

        // 根据用户名加载角色信息
        List<String> roles = userMapper.getRoleByUserName(username);

        // 根据角色加载url
        List<String> authorities = userMapper.getAuthoritesByRoles(roles);
        // 角色是一个特殊的权限 将用户角色转换成权限
        roles = roles.stream().map(role -> "ROLE_" + role).collect(Collectors.toList());
        authorities.addAll(roles);
        // 设置用户权限
        userDetails.setAuthorities(
                AuthorityUtils.commaSeparatedStringToAuthorityList(String.join(",", authorities)));
        // 通过用户角色列表加载资源权限列表
        return userDetails;

    }
}
