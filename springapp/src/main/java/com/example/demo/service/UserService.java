package com.example.demo.service;

import com.example.demo.model.Policyadding;
import com.example.demo.model.User;
import com.example.demo.repository.PolicyaddRepo;
import com.example.demo.repository.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
    private final UserRepository userRepository;
    private final PolicyaddRepo policyRepository;

    public UserService(UserRepository userRepository, PolicyaddRepo policyRepository) {
        this.userRepository = userRepository;
        this.policyRepository = policyRepository;
    }

    public User signup(String fullName, String email, String password) {
        User user = new User();
        user.setName(fullName);
        user.setEmail(email);
        user.setPassword(password);

        // Check if the user with the same email already exists
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            throw new IllegalArgumentException("User with this email already exists.");
        }

        // Save the user to the database
        return userRepository.save(user);
    }

    public User login(String email, String password) {
        // Retrieve the user from the database based on the provided email
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("User with this email does not exist.");
        }

        // Compare the provided password with the stored password
        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Incorrect password.");
        }

        return user;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Policyadding savePolicy(Policyadding policy) {
        return policyRepository.save(policy);
    }

    public List<Policyadding> getAllPolicies() {
        return policyRepository.findAll();
    }

    public Policyadding addPolicy(Policyadding policyadding) {
        return policyRepository.save(policyadding);
    }
}
