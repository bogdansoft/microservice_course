package com.webservice.service;

import com.webservice.exception.UserNotFoundException;
import com.webservice.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class UserService {

    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1, "Adam", LocalDate.now().minusYears(30)));
        users.add(new User(2, "John", LocalDate.now().minusYears(33)));
        users.add(new User(3, "Bill", LocalDate.now().minusYears(40)));
    }

    public List<User> findAll() {
        return users;
    }

    public User findById(Integer id) {
        return users.stream()
                .filter(v -> Objects.equals(v.getId(), id))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("user by id=" + id + " was not found"));
    }

    public void save(User user) {
        users.add(user);
    }

    public boolean delete(Integer id) {
        return users.removeIf(v -> Objects.equals(v.getId(), id));
    }
}
