package com.myproj.chatapp.repository;

import com.myproj.chatapp.model.Account;
import com.myproj.chatapp.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    //Optional<T> is a wrapper object in Java that may or may not contain a value.
    Optional<Account> findByUsername(String username);
    List<Account> findAllByStatus(Status status);
}
//Custom methods
//Optional<Account> findByUsername(String username)
//
//Finds a user by their username column.
//
//Used in login/authentication when Spring Security needs to load user details.
//
//        List<Account> findAllByStatus(Status status)
//
//Finds all users with a given Status (ONLINE or OFFLINE).
//
//Useful for showing “Online users” in the chat UI.