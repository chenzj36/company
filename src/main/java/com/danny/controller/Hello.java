package com.danny.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Danny Lyons
 * @Email chenzj36@live.cn
 * @Time 2020/1/14 13:14
 * @Description
 */
@RestController
public class Hello {
    @RequestMapping("/hello")
    public String hello(){
        return "hello IDEA";
    }
}
