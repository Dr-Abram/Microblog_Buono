/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blog.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Abreham
 */

@Entity
@Table(name = "comments")
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Basic
    @Getter
    @Setter
    private String titleC;

    @Basic
    @Getter
    @Setter
    private String content;

    @Getter
    @Setter
    @ManyToOne(targetEntity = Post.class)
    @JoinColumn(name = "Post_Id", nullable = false, updatable = false)
    private Post post;

    @Getter
    @Setter
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "User_Id", nullable = false, updatable = false)
    private User user;

}
