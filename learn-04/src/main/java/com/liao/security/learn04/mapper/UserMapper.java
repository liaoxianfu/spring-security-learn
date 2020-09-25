package com.liao.security.learn04.mapper;

import com.liao.security.learn04.domain.User;
import com.liao.security.learn04.domain.WebUserDetails;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
  * @author liao
  * @since 2020/9/25 16:57
  */    
public interface UserMapper {

    WebUserDetails getUserDetailsByUsername(String username);

    List<String> getRoleByUserName(String username);

    List<String> getAuthoritesByRoles(@Param("roles") List<String> roles);

    List<String> getUrlByUsername(String username);
}