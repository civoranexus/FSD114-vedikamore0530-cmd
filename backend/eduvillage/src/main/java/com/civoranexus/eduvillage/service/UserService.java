package com.civoranexus.eduvillage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.civoranexus.eduvillage.entity.User;
import com.civoranexus.eduvillage.repository.UserRepository;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);

    if (user.isPresent() && 
        passwordEncoder.matches(password, user.get().getPassword())) {
        return "Login successful";
    } else {
        return "Invalid email or password";
    }
}
     public List<User> getAllUsers() {
    return userRepository.findAll();
}


}
    