package com.revature.project3.services;

import com.revature.project3.entities.Comment;
import com.revature.project3.entities.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    private Long postId;

    @BeforeEach
    void setUp() {
        Post post = new Post();
        post.setDescription("New Post");
        post.setCommentList(new ArrayList<>());
        Post post_db = postService.addPost(post);
        postId = post_db.getId();
        return;
    }

    @Test
    // Test adding a new comment to the database:
    void testAddComment() {
        Comment comment = new Comment();
        comment.setDescription("This is a new comment");
        comment.setDate(new Date());
        comment.setAuthor("William Shakespeare");
        comment = commentService.addComment(comment, postId);
        assertEquals("This is a new comment", comment.getDescription());
        assertNotNull(comment.getId());
        Comment comment_db = commentService.findById(comment.getId());
        assertNotNull(comment_db);
    }

    @Test
    @Transactional
    // test getting comments for a specific post:
    void testGetCommentsForPost() {
        // assign 3 comments to a given post:
        Comment comment1 = new Comment();
        Comment comment2 = new Comment();
        Comment comment3 = new Comment();
        comment1.setDescription("Comment");
        comment2.setDescription("Comment");
        comment3.setDescription("Comment");
        comment1.setDate(new Date());
        comment2.setDate(new Date());
        comment3.setDate(new Date());
        comment1.setAuthor("William Shakespeare");
        comment2.setAuthor("William Shakespeare");
        comment3.setAuthor("William Shakespeare");
        commentService.addComment(comment1, postId);
        commentService.addComment(comment2, postId);
        commentService.addComment(comment3, postId);
        // make sure all 3 comments were added:
        List<Comment> commentList = commentService.getCommentsForPost(postId);
        assertEquals(3, commentList.size());
    }

    @Test
    @Transactional
    // test deleting comment
    void testDeleteComment() {
        Comment comment = new Comment();
        comment.setDescription("New description");
        comment.setDate(new Date());
        comment.setAuthor("William Shakespeare");
        Comment comment_db = commentService.addComment(comment, postId);
        Long commentIdToDelete = comment_db.getId();
        assertNotNull(commentIdToDelete);
        commentService.deleteComment(commentIdToDelete);
        try {
            Comment phantomComment = commentService.findById(commentIdToDelete);
        } catch (Exception e) {
            // make sure we can't retrieve this item:
            assertEquals(JpaObjectRetrievalFailureException.class, e.getClass());
        }
        

    }

}
