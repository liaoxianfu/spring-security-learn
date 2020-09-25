package com.liao.security.learn02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author liao
 * @since 2020/9/24 16:07
 */
@Controller
public class SecurityController {
    @GetMapping("index")
    public String index() {
        return "index";
    }

    @GetMapping("demo1")
    public String demo1() {
        return "demo1";
    }

    @GetMapping("demo2")
    public String demo2() {
        return "demo2";
    }

}
