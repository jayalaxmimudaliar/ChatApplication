package com.myproj.chatapp.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

//basically a blueprint for your database table called Account.

@Data
@NoArgsConstructor //Generate default and full-args constructors.
@AllArgsConstructor
@Entity  //Tells Spring JPA: "Make a table for this class" (table name will be account by default).
public class Account implements UserDetails {
//implements UserDetails → This is part of Spring Security.
    //login authentication.
//
//Spring Security will call the methods from UserDetails
// (like getUsername(), getPassword(), getAuthorities()) to check login details.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private Status status;

    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    public Account(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }
}
