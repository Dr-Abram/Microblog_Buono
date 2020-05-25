/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blog.apiController;

import com.blog.entities.User;
import com.blog.repositories.PostRepository;
import com.blog.repositories.UserRepository;
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
@RequestMapping("/users")
public class restUser {

    // inietto dentro userRepository il codice di UserRepository (UserRepository è una dependency)
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping(value = "{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {

        Optional<User> op = userRepository.findById(id);

        if (op.isPresent()) {
            return new ResponseEntity<User>(op.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity addUser(@RequestBody User user) throws URISyntaxException {
        
        if (user.getUsername().length()!=0 && user.getPassword().length()!=0) {
            userRepository.saveAndFlush(user);
        }
        return ResponseEntity.created(new URI("http://localhost:8084/api/users/" + user.getId())).build();
    }

    @RequestMapping(value = "{id}", method = DELETE)
    public ResponseEntity deleteUser(@PathVariable("id") Long id) {

        try {

            userRepository.deleteById(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        } catch (IllegalArgumentException e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping(value = "{id}/posts")
    public ResponseEntity getAllPost(@PathVariable("id") Long id) {
        return ResponseEntity.ok(postRepository.findByUser(userRepository.findById(id).get()));
    }

}
