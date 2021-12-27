package com.revature.project3.controllers;

import com.revature.project3.entities.Post;
import com.revature.project3.services.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostsController
{
    @Autowired
    private PostsService service;

    @PostMapping("/posts")
    public Post savePost(@RequestBody Post post) {
        return service.addPost(post);
    }

    @GetMapping("/posts/{id}")
    public Post getPostById(@PathVariable("id") long id) {
        return service.getPostById(id);
    }

    @DeleteMapping("/posts/{id}")
    public String deletePost(@PathVariable("id") long id){
        service.deletePost(id);
        return "post deleted successfully";
    }
}
