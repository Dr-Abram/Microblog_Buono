/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blog.controller;

import com.blog.entities.User;
import com.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Abreham
 */
@Controller
public class userController {
    
    @Autowired
    UserRepository userRepository;
    
    //@PostMapping("/registration")
    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public String registration(User u) {
        
        userRepository.save(u);
        
        return "profile.html";
    }
}
