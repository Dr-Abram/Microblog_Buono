/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blog.controllers;

import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.repositories.PostRepository;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Abreham
 */
@Component
@Path("/post")
public class PostController {
    
// inietto dentro userRepository il codice di UserRepository (UserRepository è una dependency)
    @Autowired
    private PostRepository postRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Post addUser(Post post) {
        return postRepository.saveAndFlush(post);
    }
    
}
