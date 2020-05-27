/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blog.apiController;

import com.blog.entities.Comment;
import com.blog.repositories.CommentRepository;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Abreham
 */
@RestController
@RequestMapping("/api/comments")
public class restComment {

    // inietto dentro commentRepository il codice di CommentRepository (CommentRepository è una dependency)
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(commentRepository.findAll());
    }

    @GetMapping(value = "{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        Optional<Comment> op = commentRepository.findById(id);

        if (op.isPresent()) {

            return new ResponseEntity<Comment>(op.get(), HttpStatus.OK);
        } else {

            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity addComment(@RequestBody Comment comment) throws URISyntaxException {

        commentRepository.saveAndFlush(comment);

        return ResponseEntity.created(new URI("http://localhost:8084/api/comments/" + comment.getId())).build();
    }

    @RequestMapping(value = "{id}", method = DELETE)
    public ResponseEntity deleteComment(@PathVariable("id") Long id) {

        try {
            
            commentRepository.deleteById(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        } catch (IllegalArgumentException e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }
    }
}