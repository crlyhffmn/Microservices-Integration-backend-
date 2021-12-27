package com.revature.project3.services;

import com.revature.project3.entities.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    public Comment addComment(Comment comment, Long postId);
    public Comment findById(Long id);
    public List<Comment> getCommentsForPost(Long postId);
    public void deleteComment(Long id);
}
