/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blog.apiController;

import com.blog.entities.Comment;
import com.blog.entities.Post;
import com.blog.repositories.CommentRepository;
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
/*
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
*/
///////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////

@RestController
@RequestMapping("/api/comments")
public class restComment {

// inietto dentro postRepository il codice di PostRepository (PostRepository è una dependency).
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;    
    
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping// metodo del prof adattato al mio progetto per fare dei test
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<JsonResponseBody> getComments() {
        return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), commentRepository.findAll()));
    }

    @GetMapping(value = "{id}", produces
            = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<restComment.JsonResponseBody> getComment(@PathVariable(value = "id") Long id) {
        Optional<Comment> op = commentRepository.findById(id);
        if (op.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new restComment.JsonResponseBody(HttpStatus.OK.value(), commentRepository.findById(id)));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new restComment.JsonResponseBody(HttpStatus.NOT_FOUND.value(), null));
        }
    }

    @PostMapping(consumes
            = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<restComment.JsonResponseBody> addComment(HttpServletRequest request,
            @RequestBody Comment comment) {

        Comment c = null;
        if (comment.getTitleC().length() != 0 && comment.getContent().length() != 0) {
            c = commentRepository.saveAndFlush(comment);
        }
//da migliorare l'implementazione di HATEOS utilizzando il supporto in Spring; 
        //      Da implementare la validazione dell'oggetto utente in input
        return ResponseEntity.status(HttpStatus.CREATED).header("location", request.
                getRequestURL().toString() + "/" + c.getId()).body(new restComment.JsonResponseBody(HttpStatus.CREATED.value(), null));
    }

    @RequestMapping(value = "{id}", method = DELETE)
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<restComment.JsonResponseBody> deleteComment(@PathVariable("id") Long id) {
        Optional<Comment> op = commentRepository.findById(id);
        if (op.isPresent()) {
            commentRepository.deleteById(id);  //se presente, cancello
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new restComment.JsonResponseBody(HttpStatus.NO_CONTENT.value(), null));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new restComment.JsonResponseBody(HttpStatus.NOT_FOUND.value(), "Comment non presente."));
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