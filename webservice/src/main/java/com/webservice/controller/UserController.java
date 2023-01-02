package com.webservice.controller;

import com.webservice.model.User;
import com.webservice.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private UserService service;

    @GetMapping
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
        service.save(user);

        return ResponseEntity.created(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(user.getId())
                        .toUri())
                .build();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        service.delete(id);
    }
}
