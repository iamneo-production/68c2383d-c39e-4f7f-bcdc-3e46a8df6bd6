package com.example.demo.controller;

import com.example.demo.model.Policyadding;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    
    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody User user) {
        User createdUser = userService.signup(user.getFullName(), user.getEmail(), user.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        User authenticatedUser = userService.login(user.getEmail(), user.getPassword());
        return ResponseEntity.ok(authenticatedUser);
    }
    
    // Other user-related endpoints
    
    // Example endpoint for getting user details by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

 
    
    @PostMapping("/policies")
    public ResponseEntity<String> createPolicy(@RequestBody Policyadding policy) {
        userService.savePolicy(policy);
        return ResponseEntity.ok("Policy added successfully!");
    }
    

        @GetMapping("/showpolicies")
        public List<Policyadding> getAllPolicies() {
            return userService.getAllPolicies();
        }

        @PostMapping
        public Policyadding addPolicy(@RequestBody Policyadding policyadding) {
            return userService.addPolicy(policyadding);
        }
    }

