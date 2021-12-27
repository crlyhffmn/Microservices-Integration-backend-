package com.revature.project3.services;

import com.revature.project3.entities.Post;
import com.revature.project3.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostsServiceImpl implements PostsService
{
    @Autowired
    private PostRepository postsRepository;

    @Override
    public Post addPost(Post post)
    {
        return (Post) postsRepository.save(post);
    }

    @Override
    public Post getPostById(long id)
    {
        Optional<Post> post = postsRepository.findById(id);
        if(!post.isPresent()) return null;
        return post.get();
    }

    @Override
    public void deletePost(long id)
    {
        postsRepository.deleteById(id);
    }
}
