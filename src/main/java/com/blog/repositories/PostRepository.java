/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blog.repositories;

import com.blog.entities.Post;
import com.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Abreham
 */

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
    public Iterable<Post> findByUser(User user);
}
