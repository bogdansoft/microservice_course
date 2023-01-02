package com.webservice.service;

import com.webservice.exception.UserNotFoundException;
import com.webservice.model.User;
import com.webservice.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserH2Service {

    private final UserRepository repository;

    public List<User> findAllFromDB() {
        return repository.findAll();
    }

    public User findByIdFromDB(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("user by id=" + id + " was not found"));
    }

    public void saveFromDB(User user) {
        repository.save(user);
    }

    public void deleteFromDB(Integer id) {
        repository.deleteById(id);
    }
}
