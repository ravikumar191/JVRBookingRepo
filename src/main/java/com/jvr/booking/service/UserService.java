package com.jvr.booking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jvr.booking.model.User;
import com.jvr.booking.repo.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String registerUser(User user) {

        // Check if phone number already exists
        if (userRepository.existsByPhone(user.getPhone())) {
            return "Error: Phone number already in use!";
        }

        // Optional email check if provided
        if (user.getEmail() != null && userRepository.existsByEmail(user.getEmail())) {
            return "Error: Email already in use!";
        }

        // Check if password matches confirm password
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return "Error: Password and Confirm Password do not match!";
        }

        // Save user and address
        userRepository.save(user);
        return "User registered successfully!";
    }
}
