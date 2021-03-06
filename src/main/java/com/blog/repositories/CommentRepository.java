/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blog.repositories;

import com.blog.entities.Comment;
import com.blog.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Abreham
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
    
    public Iterable<Comment> findByPost(Post post);
}
