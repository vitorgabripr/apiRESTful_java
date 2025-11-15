package com.projeto.crud.controller;

import com.projeto.crud.model.AuthRequest;
import com.projeto.crud.model.UserInfo;
import com.projeto.crud.service.JwtService;
import com.projeto.crud.service.UserInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.web.bind.annotation.*;

public class UserController {
    private UserInfoService userInfoService;
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;

    @getMapping("/welcome")
    public String welcome(){
        return "Welcome to JWT Authentication";
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody UserInfo userInfo){
        return userInfoService.addUser(userInfo);
    }
    
    //removed the role checks here as they are alredy managed in securityconfig

    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        if(authentication.isAuthenticated()){
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }
}
