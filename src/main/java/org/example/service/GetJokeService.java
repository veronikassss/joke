package org.example.service;

import org.example.repository.DefaultUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetJokeService {

    private DefaultUserRepository defaultUserRepository;

    @Autowired
    public GetJokeService(DefaultUserRepository defaultUserRepository) {
        this.defaultUserRepository = defaultUserRepository;
    }


}
