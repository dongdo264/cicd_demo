package com.example.demo.models.request;

import lombok.Data;

import java.util.Set;

@Data
public class RegisterData {
    private int id;
    private String username;
    private String password;
    private String rePassword;

    private Set<Integer> roles;

}
