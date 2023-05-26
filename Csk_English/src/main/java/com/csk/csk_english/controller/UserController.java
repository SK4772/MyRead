package com.csk.csk_english.controller;

import com.csk.csk_english.domain.User;
import com.csk.csk_english.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserServiceImpl userService;

//    @GetMapping("/word/getUserRecord")
//    public int getUserRecord(int username){
//        return userService.getUserRecond();
//    }

    @PostMapping("/user/login")
    public Object login(@RequestBody User user){
        return userService.login(user);
    }

    @PostMapping("/user/register")
    public Object register(@RequestBody User user){
        return userService.register(user.getUsername(),user.getPassword());
    }

    @PostMapping("/user/update")
    public Object update(@RequestBody User user){
        return userService.update(user.getUsername(),user.getPassword(),user.getName(),user.getTelephone(),user.getMail(),user.getUserid());
    }

}
