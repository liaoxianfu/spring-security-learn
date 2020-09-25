package com.liao.security.learn04.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liao
 * @since 2020/9/25 17:24
 */
@RestController
public class HelloController {
    @GetMapping("index")
    public String index() {
        return "index";
    }
}
