package com.example.demo.Controller;

import com.example.demo.Entity.User;
import com.example.demo.Repository.InterfaceUserRepository;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
public class UserController {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private InterfaceUserRepository userRepositoryJpa;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<User> getUserEmail(@PathVariable(value = "email") Long userId)
            throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + userId));
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/register")
    public Long registerUser(@Valid @RequestBody User user) {
        User u = userRepository.save(user);
        return u.getId();
    }

    @PostMapping("/login")
    public Long loginUser(@RequestBody User u) {
        Long id = userRepositoryJpa.getUser(u.getEmail(), u.getPassword());
        return id;
    }


}
