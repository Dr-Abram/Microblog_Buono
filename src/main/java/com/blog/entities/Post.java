/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blog.entities;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ManyToAny;

/**
 *
 * @author Abreham
 */
@Entity
@Table(name = "posts")
public class Post implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Basic
    @Getter
    @Setter
    private String title;

    @Basic
    @Getter
    @Setter
    private String content;

    @Getter
    @Setter
    @ManyToOne(targetEntity = User.class)
    private User user;
    
}
