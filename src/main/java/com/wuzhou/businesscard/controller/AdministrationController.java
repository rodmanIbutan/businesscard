package com.wuzhou.businesscard.controller;

import com.wuzhou.businesscard.entity.Card;
import com.wuzhou.businesscard.entity.Status;
import com.wuzhou.businesscard.entity.User;
import com.wuzhou.businesscard.service.AdministrationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "administration")
public class AdministrationController {
    final
    AdministrationService administrationService;

    public AdministrationController(AdministrationService administrationService) {
        this.administrationService = administrationService;
    }

    @GetMapping(value = "/getUserList")
    @ResponseBody
    public Status<List<User>> getUserList(@RequestHeader("token") String token) {
        return administrationService.getUserList(token);
    }
    @GetMapping(value = "/getUserById")
    @ResponseBody
    public Status<User> getUserById(@RequestParam Integer id, @RequestHeader("token") String token) {
        return administrationService.getUserById(id,token);
    }
    @PostMapping(value = "/createUser")
    @ResponseBody
    public Status<Integer> createUser(@RequestBody User user, @RequestHeader("token") String token) {
        return administrationService.createUser(user,token);
    }
    @PostMapping(value = "/updateUser")
    @ResponseBody
    public Status<Integer> updateUser(@RequestBody User user, @RequestHeader("token") String token) {
        return administrationService.updateUser(user  ,token);
    }
    @GetMapping(value = "/deleteUser")
    @ResponseBody
    public Status<Integer> deleteUser(@RequestParam Integer id, @RequestHeader("token") String token) {
        return administrationService.deleteUser(id,token);
    }
    @GetMapping(value = "/getCardList")
    @ResponseBody
    public Status<List<Card>> getCardList(@RequestHeader("token") String token){
        return administrationService.getAllCard(token);
    }
}
