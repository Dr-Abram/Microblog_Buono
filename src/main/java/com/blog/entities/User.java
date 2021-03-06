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

/**
 *
 * @author Abreham
 */
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;
    
    @Basic
    @Getter
    @Setter
    private String username;
    
    @Basic
    @Getter
    @Setter
    private String email;
    
    @Basic
    @Getter
    @Setter
    private String password;
    
}
