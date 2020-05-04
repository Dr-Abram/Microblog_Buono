/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blog;

import com.blog.controllers.CommentController;
import com.blog.controllers.PostController;
import com.blog.controllers.UserController;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

/**
 *
 * @author Abreham
 */
@Component
@ApplicationPath("${spring.jersey.application-path:/}")
public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig(){
        this.register(UserController.class);
        this.register(PostController.class);
        this.register(CommentController.class);
    }
}