package com.civoranexus.eduvillage.service;

import org.springframework.stereotype.Service;
import com.civoranexus.eduvillage.entity.User;
import com.civoranexus.eduvillage.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
        return userRepository.save(user);
    }
}
