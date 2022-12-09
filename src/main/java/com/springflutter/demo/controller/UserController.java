package com.springflutter.demo.controller;


import com.springflutter.demo.entity.User;
import com.springflutter.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user) {
        return userService.registerNewUser(user);
    }

    @PostMapping({"/registerNewUsers"})
    public String registerNewUsers(@RequestBody User[] users){
        String response = "";
        for(User user : users){
            userService.registerNewUser(user);
            response += user.getUsername() + " registered successfully\n";
        }
        return response;


    }

}
