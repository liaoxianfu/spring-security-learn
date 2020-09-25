package com.liao.securtity.learn01.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liao
 * @since 2020/9/24 11:39
 */
@RestController
public class HelloController {

    @GetMapping("hello")
    public String hello() {
        return "spring security";
    }
}
