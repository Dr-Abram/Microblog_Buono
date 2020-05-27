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
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/api/users")

public class restUser {

    /*
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
    }*/
    // inietto dentro userRepository il codice di UserRepository (UserRepository è una dependency)
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @RequestMapping("/hello")
    public String sayHello() {
        return "Hello everyone!";
    }

// ========================================================================== \\
//\\//\\//\\//\\    Trovare tutti gli utenti    //\\//\\//\\//\\//\\//\\//\\//\\
// ========================================================================== \\
    /*@GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(userRepository.findAll());
    }*/
    @GetMapping// metodo del prof adattato al mio progetto per fare dei test
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<JsonResponseBody> getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), userRepository.findAll()));
    }

// ========================================================================== \\
//\\//\\//\\//\\    Trovare utente dato l'id    //\\//\\//\\//\\//\\//\\//\\//\\
// ========================================================================== \\
    /*@GetMapping(value = "{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {

        Optional<User> op = userRepository.findById(id);

        if (op.isPresent()) {

            return new ResponseEntity<User>(op.get(), HttpStatus.OK);
        } else {

            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }*/
    @GetMapping(value = "{id}", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResponseBody> getUser(@PathVariable(value = "id") Long id) {
        Optional<User> op = userRepository.findById(id);
        if (op.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), userRepository.findById(id)));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new JsonResponseBody(HttpStatus.NOT_FOUND.value(), null));
        }
    }

// ========================================================================== \\
//\\//\\//\\//\\    Caricare un nuovo utente    //\\//\\//\\//\\//\\//\\//\\//\\
// ========================================================================== \\
    /*@PostMapping(consumes = MediaType.APPLICATION_JSON)
    public ResponseEntity addUser(@RequestBody User user) throws URISyntaxException {

        userRepository.saveAndFlush(user);

        return ResponseEntity.created(new URI("http://localhost:8084/api/users/" + user.getId())).build();
    }*/
    @PostMapping(consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<JsonResponseBody> addUtente(HttpServletRequest request,
            @RequestBody User user) {
        User u = null;
        if (user.getUsername().length() != 0 && user.getPassword().length() != 0) {
            u = userRepository.saveAndFlush(user);
        }

        //da migliorare l'implementazione di HATEOS utilizzando il supporto in Spring; 
        //      Da implementare la validazione dell'oggetto utente in input
        return ResponseEntity.status(HttpStatus.CREATED).header("location", request.
                getRequestURL().toString() + "/" + u.getId()).body(new JsonResponseBody(HttpStatus.CREATED.value(), null));
    }
// ========================================================================== \\
//\\//\\//\\//\\   Cancellare utente dato l'id  //\\//\\//\\//\\//\\//\\//\\//\\
// ========================================================================== \\

    /*@RequestMapping(value = "{id}", method = DELETE)
    public ResponseEntity deleteUser(@PathVariable("id") Long id) {

        try {

            userRepository.deleteById(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        } catch (IllegalArgumentException e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }
    }*/
    @RequestMapping(value = "{id}", method = DELETE)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<JsonResponseBody> deleteUtente(@PathVariable("id") Long id) {
        Optional<User> op = userRepository.findById(id);
        if (op.isPresent()) {
            userRepository.deleteById(id);  //se presente, cancello
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new JsonResponseBody(HttpStatus.NO_CONTENT.value(), null));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new JsonResponseBody(HttpStatus.NOT_FOUND.value(), "Utente non presente."));
        }
    }
// ========================================================================== \\
//\\//\\//\\//\\    Trovare utente dato l'id    //\\//\\//\\//\\//\\//\\//\\//\\
// ========================================================================== \\

    /*@GetMapping(value = "{id}/posts")
    public ResponseEntity getAllPost(@PathVariable("id") Long id) {
        return ResponseEntity.ok(postRepository.findByUser(userRepository.findById(id).get()));
    } */
    //----------Definizione JsonResponseBody----------
    @AllArgsConstructor
    class JsonResponseBody {

        @Getter
        @Setter
        private int server;
        @Getter
        @Setter
        private Object response;
    }
    //------------------------------------------------
}
