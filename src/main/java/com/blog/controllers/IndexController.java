/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Abreham
 */

@Controller
public class IndexController {
    @RequestMapping("/")
    public String index() {
        return "Spring Boot Example";
    }
}
