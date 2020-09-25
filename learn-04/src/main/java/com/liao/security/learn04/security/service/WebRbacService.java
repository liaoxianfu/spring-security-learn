package com.liao.security.learn04.security.service;

import com.liao.security.learn04.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @description:
 * @author: Andy
 * @time: 2020/5/16 11:29
 */
@Slf4j
@Component("rbacService")
public class WebRbacService {

    @Resource
    private UserMapper userDao;
    public static AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 判断用户是否具有request的访问权限
     */
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {

        boolean match = false;
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            String username = userDetails.getUsername();
            String requestUri = request.getRequestURI();
            // 获取该用户能够有权访问的url
            List<String> urls = userDao.getUrlByUsername(username);
            match = urls.stream().anyMatch(
                    // 判断以*结尾的数据能够访问任意的数据
                    url -> {
                        log.info("获得的uri为{}", requestUri);
                        if (url.endsWith("/*")) {
                            String replace = url.replace("/*", "");
                            return requestUri.startsWith(replace);
                        }
                        return antPathMatcher.match(url, requestUri);
                    }
            );
        }
        return match;
    }

}
