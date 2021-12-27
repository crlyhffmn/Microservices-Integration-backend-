package com.revature.project3.services;

import com.revature.project3.entities.Post;
import org.springframework.stereotype.Service;

@Service
public interface PostsService
{
    public Post addPost(Post post);
    public Post getPostById(long id);
    public void deletePost(long id);
}
