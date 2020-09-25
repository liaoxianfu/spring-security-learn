package com.liao.security.learn04;

import com.liao.security.learn04.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author liao
 * @since 2020/9/25 11:35
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RBACApplicationTest {

    @Resource
    private UserMapper userMapper;

    @Resource
    private PasswordEncoder encoder;


    @Test
    public void test01() {
        // $2a$10$fC0NAALTPE3OEZwYedkIwOfXpqmdL6ZepLqm0SYbqmyu00swvBiKO
        System.out.println(encoder.encode("123456"));
    }



}
