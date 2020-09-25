package com.liao.security.learn02.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liao.security.learn02.config.R;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义登录成功后的处理
 *
 * @author liao
 * @since 2020/9/24 17:37
 */
@Configuration
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    /**
     * 对象转换为json
     */
    @Resource
    private ObjectMapper objectMapper;

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        R success = R.success().data("登录成功");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(success));
    }
}
