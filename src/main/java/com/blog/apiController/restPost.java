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
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
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
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 *
 * @author Abreham
 */
@RestController
@RequestMapping("/api/posts")
public class restPost {

    /*
// inietto dentro postRepository il codice di PostRepository (PostRepository è una dependency).
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

        if (post.getTitle().length()!=0 && post.getContent().length()!=0) {
            postRepository.saveAndFlush(post);
        }
        
        return ResponseEntity.created(new URI("http://localhost:8084/api/posts/" + post.getId())).build();
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

}*/
// inietto dentro postRepository il codice di PostRepository (PostRepository è una dependency).
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping// metodo del prof adattato al mio progetto per fare dei test
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<JsonResponseBody> getPosts() {
        return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), postRepository.findAll()));
    }

    @GetMapping(value = "{id}", produces
            = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<restPost.JsonResponseBody> getPost(@PathVariable(value = "id") Long id) {
        Optional<Post> op = postRepository.findById(id);
        if (op.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new restPost.JsonResponseBody(HttpStatus.OK.value(), postRepository.findById(id)));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new restPost.JsonResponseBody(HttpStatus.NOT_FOUND.value(), null));
        }
    }

    @PostMapping(consumes
            = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<restPost.JsonResponseBody> addPost(HttpServletRequest request,
            @RequestBody Post post) {

        Post p = null;
        if (post.getTitle().length() != 0 && post.getContent().length() != 0) {
            p = postRepository.saveAndFlush(post);
        }
//da migliorare l'implementazione di HATEOS utilizzando il supporto in Spring; 
        //      Da implementare la validazione dell'oggetto utente in input
        return ResponseEntity.status(HttpStatus.CREATED).header("location", request.
                getRequestURL().toString() + "/" + p.getId()).body(new restPost.JsonResponseBody(HttpStatus.CREATED.value(), null));
    }

    @RequestMapping(value = "{id}", method = DELETE)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<restPost.JsonResponseBody> deletePost(@PathVariable("id") Long id) {
        Optional<Post> op = postRepository.findById(id);
        if (op.isPresent()) {
            postRepository.deleteById(id);  //se presente, cancello
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new restPost.JsonResponseBody(HttpStatus.NO_CONTENT.value(), null));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new restPost.JsonResponseBody(HttpStatus.NOT_FOUND.value(), "Post non presente."));
        }
    }

    /*
    @GetMapping(value = "{id}/comments")
    public ResponseEntity getAllComment(@PathVariable("id") Long id) {

        return ResponseEntity.ok(commentRepository.findByPost(postRepository.findById(id).get()));
    }
     */
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
