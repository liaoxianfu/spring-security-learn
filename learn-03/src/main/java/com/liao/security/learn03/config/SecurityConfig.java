package com.liao.security.learn02.config;

import com.liao.security.learn02.handler.LoginSuccessHandler;
import com.liao.security.learn02.handler.LoginFailHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.annotation.Resource;

/**
 * @author liao
 * @since 2020/9/24 15:54
 */

@EnableWebSecurity // 自定义security配置
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private LoginSuccessHandler successHandler;

    @Resource
    private LoginFailHandler failHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                // 取消csrf的限制 否者无法加载页面
                        csrf().disable()
                .formLogin()
                .loginPage("/login.html")
                // 进行登录处理的URL 也就是form表单的action登录url 需要在antMatchers中允许访问 不进行拦截
                .loginProcessingUrl("/login")
                .successHandler(successHandler)
                .failureHandler(failHandler)
                .and()
                // 验证规则
                .authorizeRequests()
                // 允许所有用户包括未登录访问
                .antMatchers("/login.html", "/login").permitAll()
                // 允许已经访问的人进行登录
                .antMatchers("/index").authenticated()
                // 允许admin角色用户登录
                .antMatchers("/demo1").hasAnyRole("admin")
                // 角色本身也是一种特殊的权限 当用户属于一种角色后就会默认存在一个ROLE_角色名的权限
                // 也就是允许拥有ROLE_admin和ROLE_user权限的用户登录 即角色为admin或者user的用户登录
                .antMatchers("/demo2").hasAnyAuthority("ROLE_admin", "ROLE_demo");

    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                // 定义用户名
//                .withUser("admin")
//                // 使用自定义的passwordEncoder加密密码
//                .password(passwordEncoder.encode("123456"))
//                .roles("admin")
//                .and()
//                .withUser("demo")
//                .password(passwordEncoder.encode("1234"))
//                .roles("demo");
//    }

    /**
     * 角色区分大小写
     * @return UserDetailsService
     */
    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("admin")
                .password(passwordEncoder.encode("123456"))
                .roles("admin").build()
        );
        manager.createUser(User.withUsername("demo")
                .password(passwordEncoder.encode("1234"))
                .roles("demo").build()
        );
        return manager;
    }
    

}
