package com.wuzhou.businesscard.controller;

import com.wuzhou.businesscard.entity.Status;
import com.wuzhou.businesscard.entity.User;
import com.wuzhou.businesscard.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "user")
public class UserController {
    final
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    @ResponseBody
    Status<User> login (@RequestBody User user) {
        return userService.login(user);
    }
}
