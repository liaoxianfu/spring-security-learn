package com.liao.securtity.learn03.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liao.securtity.learn03.config.R;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author liao
 * @since 2020/9/24 17:50
 */

@Configuration
public class LoginFailHandler implements AuthenticationFailureHandler {
    @Resource
    private ObjectMapper objectMapper;

    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        R data = R.error().data("登录失败");
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(data));
    }
}
