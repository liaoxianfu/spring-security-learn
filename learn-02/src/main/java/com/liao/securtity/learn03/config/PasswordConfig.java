package com.liao.securtity.learn03.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author liao
 * @since 2020/9/24 16:19
 */
@Configuration
public class PasswordConfig {

    /**
     * 自定义用户密码的加密方式 常用的是BCryptPasswordEncoder
     * @return BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
