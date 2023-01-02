package com.webservice.controller;

import com.webservice.model.Post;
import com.webservice.service.PostH2Service;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/db/posts")
@AllArgsConstructor
public class PostH2Controller {
    private final PostH2Service service;

    @GetMapping
    public List<Post> retrieveAllUsers() {
        return service.findAllFromDB();
    }

    @GetMapping("/{id}")
    public EntityModel<Post> getUserById(@PathVariable Integer id) {
        var post = service.findByIdFromDB(id);

        EntityModel<Post> entityModel = EntityModel.of(post);
        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-posts"));

        return entityModel;
    }

    @PostMapping
    public ResponseEntity<Post> saveUser(@Valid @RequestBody Post post) {
        service.saveFromDB(post);

        return ResponseEntity.created(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(post.getId())
                        .toUri())
                .build();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        service.deleteFromDB(id);
    }
}
