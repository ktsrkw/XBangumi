package com.wt.controller;

import com.wt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    @Qualifier("userServiceImpl")
    UserService userService;

    @GetMapping("/test")
    public String getUserTest(){
        return userService.getUserById(1).toString();
    }
}
