/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blog.apiController;

import com.blog.entities.Post;
import com.blog.repositories.PostRepository;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 *
 * @author Abreham
 */
@RestController
@RequestMapping("/api/v1/posts")
public class restPost {
    
// inietto dentro userRepository il codice di UserRepository (UserRepository è una dependency)
    @Autowired
    private PostRepository postRepository;

    @GetMapping
    public List<Post> getAll() {
        return postRepository.findAll();
    }
    
    @GetMapping(value = "{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        
        Optional<Post> op = postRepository.findById(id);
        
        if (op.isPresent()) {
   
            return new ResponseEntity<Post>(op.get(), HttpStatus.OK);
        } else {
            
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity addPost(@RequestBody Post post) {
        
        postRepository.saveAndFlush(post);
        
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "{id}", method = DELETE)
    public ResponseEntity deletePost(@PathVariable("id") Long id){
        
        try {
            
            postRepository.deleteById(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            
        } catch (IllegalArgumentException e) {
            
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
            
        }
    }
    
}
