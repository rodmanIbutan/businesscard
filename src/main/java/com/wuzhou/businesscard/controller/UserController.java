package com.wuzhou.businesscard.controller;

import com.wuzhou.businesscard.entity.Status;
import com.wuzhou.businesscard.entity.User;
import com.wuzhou.businesscard.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    @PostMapping("/updatePassword")
    @ResponseBody
    Status<Integer> updatePassword (@RequestBody Map<String,String> map, @RequestHeader("token") String token) {
        String password = map.get("password");
        String npassword = map.get("npassword");
        return userService.updatePassword(token, password, npassword);
    }
}
