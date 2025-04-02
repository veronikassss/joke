package org.example.repository;

import org.example.models.User;

import java.util.Set;

public interface UserRepository {
     void saveUser(User user);
     boolean existsByLogin(String username);
     User find(String login);
}
