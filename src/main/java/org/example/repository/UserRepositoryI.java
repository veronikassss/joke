package org.example.repository;

import org.example.models.User;

import java.util.Set;

public interface UserRepositoryI {
     void saveUser(User user);
     boolean existsByLogin(String username);
     User findOrCreate(String login);
}
