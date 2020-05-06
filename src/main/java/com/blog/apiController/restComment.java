/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blog.apiController;

import com.blog.entities.Comment;
import com.blog.repositories.CommentRepository;
import java.util.List;
import java.util.Optional;
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
@Path("/comment")
public class restComment {
    
    // inietto dentro userRepository il codice di UserRepository (UserRepository è una dependency)
    @Autowired
    private CommentRepository commentRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Optional<Comment> getById(Long id) {
        return commentRepository.findById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Comment addUser(Comment comment) {
        return commentRepository.saveAndFlush(comment);
    }
}
