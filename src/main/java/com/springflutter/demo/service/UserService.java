package com.springflutter.demo.service;


import com.springflutter.demo.dao.RoleDao;
import com.springflutter.demo.dao.UserDao;
import com.springflutter.demo.entity.Role;
import com.springflutter.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public  User registerNewUser(User user){
        Role role = roleDao.findById("user").get();

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        user.setPassword(getEncodedPassword(user.getPassword()));

        return userDao.save(user);

    }



    /**
     *  This method creates the roles and 2 test users
     */
    @PostConstruct
    public void initRoles(){

        roleDao.save(new Role("admin", "Admin role"));
        roleDao.save(new Role("user", "Default role for newly created record"));


    }
    public String getEncodedPassword(String password){
        return passwordEncoder.encode(password);
    }


}
