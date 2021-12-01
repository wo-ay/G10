package com.coursedemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controllertest {

    //spring-boot-starter-web的默认端口为8080
    @GetMapping("/test1")
    public String test() {
        return "it is a test!!";
    }
}
