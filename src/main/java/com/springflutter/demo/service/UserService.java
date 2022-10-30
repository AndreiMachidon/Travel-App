package com.springflutter.demo.service;


import com.springflutter.demo.dao.RoleDao;
import com.springflutter.demo.dao.UserDao;
import com.springflutter.demo.entity.Role;
import com.springflutter.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerNewUser(User user){
        Role role = roleDao.findById("user").get();

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        user.setPassword(getEncodedPassword(user.getPassword()));

        return userDao.save(user);

    }

    //this method creates the roles in the tables when the application runs
    public void initRolesAndUser(){

        Role adminRole = new Role();
        adminRole.setRoleName("admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("user");
        userRole.setRoleDescription("Default role for newly created record");
        roleDao.save(userRole);

        User adminUser = new User();
        adminUser.setUsername("machi");
        adminUser.setFirstName("Andrei");
        adminUser.setLastName("Machidon");
        adminUser.setPassword(getEncodedPassword("password123"));
        Set<Role> adminRoles =new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRoles(adminRoles);
        userDao.save(adminUser);


        User user = new User();
        user.setUsername("rusu");
        user.setFirstName("Vlad");
        user.setLastName("Rusu");
        user.setPassword(getEncodedPassword("password321"));
        Set<Role> userRoles =new HashSet<>();
        userRoles.add(userRole);
        user.setRoles(userRoles);
        userDao.save(user);


    }

    public String getEncodedPassword(String password){
        return passwordEncoder.encode(password);
    }
}
