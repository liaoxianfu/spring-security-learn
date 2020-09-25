package com.liao.security.learn04;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author liao
 * @since 2020/9/25 16:54
 */

@MapperScan("com.liao.security.learn04.mapper")
@SpringBootApplication
public class RbacApplication {
    public static void main(String[] args) {
        SpringApplication.run(RbacApplication.class, args);
    }
}
