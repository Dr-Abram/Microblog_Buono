/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blog.apiController;

import com.blog.entities.Post;
import com.blog.repositories.PostRepository;
import com.blog.repositories.CommentRepository;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/posts")
public class restPost {

// inietto dentro postRepository il codice di PostRepository (PostRepository è una dependency)
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(postRepository.findAll());
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
    public ResponseEntity addPost(@RequestBody Post post) throws URISyntaxException {

        postRepository.saveAndFlush(post);

        return ResponseEntity.created(new URI("http://localhost:8084/api/v1/posts/" + post.getId())).build();
    }

    @RequestMapping(value = "{id}", method = DELETE)
    public ResponseEntity deletePost(@PathVariable("id") Long id) {

        try {

            postRepository.deleteById(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        } catch (IllegalArgumentException e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping(value = "{id}/comments")
    public ResponseEntity getAllComment(@PathVariable("id") Long id) {

        return ResponseEntity.ok(commentRepository.findByPost(postRepository.findById(id).get()));
    }

}
