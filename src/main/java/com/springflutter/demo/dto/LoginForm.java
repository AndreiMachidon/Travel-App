package com.springflutter.demo.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
public class LoginForm {
    @NotBlank
    private String username;

    @NotBlank
    private String password;


}
