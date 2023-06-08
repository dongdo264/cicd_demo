package com.example.demo.controllers;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/accounts")
public class UserController {
    @Autowired
    private UserRepository accountRepository;
    @GetMapping("/test")
    @ResponseBody
    public Iterable<UserEntity> getAllAccounts() {
        // This returns a JSON or XML with the users
        return accountRepository.findAll();
    }



}
