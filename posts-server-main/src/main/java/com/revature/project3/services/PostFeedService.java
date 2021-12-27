package com.revature.project3.services;

import com.revature.project3.entities.Post;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface PostFeedService {
    public Post addPost(Post new_post); //FOR TESTING
    public List<Post> getAllPosts();
    public List<Post> getAllPostsByOwner(long user_id);
    public List<Post> getAllPostsBefore(Date bench);
    public List<Post> getAllPostsAfter(Date bench);
    public List<Post> getAllPostsContaining(String searchString);
}
