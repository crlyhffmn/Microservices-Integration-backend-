package com.revature.project3.services;

import com.revature.project3.entities.Post;
import org.springframework.stereotype.Service;

@Service
public interface PostService {
    public Post addPost(Post post);
}
