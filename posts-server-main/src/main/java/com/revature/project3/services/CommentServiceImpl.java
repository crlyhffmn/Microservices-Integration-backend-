package com.revature.project3.services;

import com.revature.project3.entities.Comment;
import com.revature.project3.entities.Post;
import com.revature.project3.repositories.CommentRepository;
import com.revature.project3.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    @Transactional
    public Comment addComment(Comment comment, Long postId) {
        Post post_db = postRepository.findById(postId).get();
        List<Comment> comments_db = post_db.getCommentList();
        comments_db.add(comment);
        post_db.setCommentList(comments_db);
        postRepository.save(post_db);
        // now, get the comment from the db:
        post_db = postRepository.getById(postId);
        Comment comment_db = post_db.getCommentList().get(post_db.getCommentList().size()-1);
        return comment_db;
    }

    @Override
    @Transactional
    public Comment findById(Long id) {
        return commentRepository.getById(id);
    }

    @Override
    @Transactional
    public List<Comment> getCommentsForPost(Long postId) {
        Post post_db = postRepository.getById(postId);
        List<Comment> commentList = post_db.getCommentList();
        Collections.sort(commentList);
        return commentList;
    }

    @Override
    @Transactional
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
