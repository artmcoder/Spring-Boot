package ru.spring.git.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.spring.git.models.Post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@Slf4j
public class PostRestController {
    private List<Post> posts = new ArrayList<>();
    private int currentId = 0;

    @GetMapping
    public ResponseEntity<List<Post>> getAll() {
        log.info("Get all Posts");
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Post> create(@RequestBody Post post) {
        post.setCreationTime(LocalDateTime.now());
        post.setId(++currentId);
        log.info("Save new {}", post);
        posts.add(post);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }
}
