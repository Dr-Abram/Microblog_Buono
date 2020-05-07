/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blog.apiController;

import com.blog.entities.Comment;
import com.blog.repositories.CommentRepository;
import java.util.Optional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Abreham
 */
@RestController
@RequestMapping("/comment")
public class restComment {
    
    // inietto dentro userRepository il codice di UserRepository (UserRepository è una dependency)
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(commentRepository.findAll());
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
