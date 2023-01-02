package com.webservice.service;

import com.webservice.exception.UserNotFoundException;
import com.webservice.model.Post;
import com.webservice.model.User;
import com.webservice.repo.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostH2Service {
    private final PostRepository repository;

    public List<Post> findAllFromDB() {
        return repository.findAll();
    }

    public Post findByIdFromDB(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("post with id=" + id + " was not found"));
    }

    public void saveFromDB(Post post) {
        repository.save(post);
    }

    public void deleteFromDB(Integer id) {
        repository.deleteById(id);
    }
}
