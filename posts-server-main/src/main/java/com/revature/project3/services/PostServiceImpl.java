package com.revature.project3.services;

import com.revature.project3.entities.Post;
import com.revature.project3.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    PostRepository repository;

    @Override
    @Transactional
    public Post addPost(Post post) {
        return repository.save(post);
    }
}
