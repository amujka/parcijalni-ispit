package com.algebra.parcijalni_ispit.user;

public interface UserService {

    User findById(Long id);
    User findByUsername(String username);
}