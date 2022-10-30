package com.springflutter.demo.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {

    @Id
    private String username;
    private String firstName;
    private String lastName;
    private String password;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE", //creates a third table with the name USER_ROLE which combines the username with the role of that user
            joinColumns = {
                @JoinColumn(name="USER_ID") //creates a column named user_id which will take the user id
    },
            inverseJoinColumns = {
                @JoinColumn(name = "ROLE_ID") //creates a column role_id which will the take id of the role
            }
    )
    private Set<Role> roles;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
