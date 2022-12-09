package com.springflutter.demo.entity;

import com.springflutter.demo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JwtResponse {


    private User user;
    private String jwtToken;

}
