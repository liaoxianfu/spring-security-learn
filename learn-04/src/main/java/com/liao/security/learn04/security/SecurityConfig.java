package com.liao.security.learn04.security;

import com.liao.security.learn04.handler.LoginFailHandler;
import com.liao.security.learn04.handler.LoginSuccessHandler;
import com.liao.security.learn04.security.service.WebUserDetailService;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * @author liao
 * @since 2020/9/25 17:05
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private WebUserDetailService userDetailService;

    @Resource
    private PasswordEncoder passwordEncoder;


    @Resource
    private LoginSuccessHandler successHandler;

    @Resource
    private LoginFailHandler failHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin().loginPage("/login.html")
                .loginProcessingUrl("/login")
                .successHandler(successHandler).failureHandler(failHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/login.html","/login").permitAll()
                .antMatchers("/index").authenticated()
                // 允许admin用户访问/下所有的资源
                .antMatchers("/*").hasAnyRole("admin")
                // 使用自定义的权限控制
                .anyRequest().access("@rbacService.hasPermission(request,authentication)");
    }
}
