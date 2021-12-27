package com.revature.project3.repositories;

import com.revature.project3.entities.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostFeedRepositoryTest {

    @Autowired
    private PostFeedRepository feedRepository;

    @Transactional
    @Test
    public void getPostsByOwnerTest(){
        List<Post> posts = feedRepository.findByUserID(1);
        System.out.println(posts);
    }

    @Transactional
    @Test
    public void getPostsByContentTest(){
        List<Post> posts = feedRepository.findByContentContaining("Krab");
        System.out.println(posts);
    }

    @Transactional
    @Test
    public void getPostsByContentCaseTest(){
        List<Post> posts = feedRepository.findByContentContaining("BeSt"); //Case-insensitive
        System.out.println(posts);
    }

    @Transactional
    @Test
    public void getPostsByDateBeforeTest(){
        Date rightnow = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println(formatter.format(rightnow));
        List<Post> posts = feedRepository.findByDateBefore(rightnow); //Get all the dates before now, which should be all
        System.out.println(posts);
    }

    @Transactional
    @Test
    public void getPostsByDateAfterTest(){
        Date rightnow = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println(formatter.format(rightnow));
        List<Post> posts = feedRepository.findByDateAfter(rightnow); //Get all the dates after now, which should be empty.
        System.out.println(posts);
    }
}