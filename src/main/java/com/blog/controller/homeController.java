/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Abreham
 */
@Controller
public class homeController {

    @RequestMapping("/")
    /**
     * HOME PAGE
     */
    public String getHomePage() {
        return "index.html";
    }

    /**
     * REGISTRATION PAGE
     */
    @RequestMapping("/register")
    public String getRegistrationPage() {
        return "register.html";
    }

    /**
     * Go to login page
     *
     * @return HTML page - login page
     */
    @RequestMapping("/login")
    public String getLoginPage() {
        return "login.html";
    }

    /**
     * Go to logout page
     *
     * @return HTML page - logout page
     */
    @GetMapping("/logout")
    public String logout() {
        return "logout.html";
    }

    /**
     * Go to login page
     *
     * @return HTML page - login page
     */
    @GetMapping("/login")
    public String login() {
        return "login.html";
    }
}
