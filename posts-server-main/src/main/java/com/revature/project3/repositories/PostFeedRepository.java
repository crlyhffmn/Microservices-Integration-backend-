package com.revature.project3.repositories;

import com.revature.project3.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface PostFeedRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p where p.userId = ?1")
    public List<Post> findByUserID(long uid); //Get the posts made by a given user

    @Query("select p from Post p where p.title like concat('%',?1,'%') or p.description like concat('%',?1,'%')")
    public List<Post> findByContentContaining(String search); //Get the posts where part of the title/desc matches the search string

    @Query("select p from Post p where p.creationDate <= ?1")
    public List<Post> findByDateBefore(Date benchmark); //Get all the posts made BEFORE the given date

    @Query("select p from Post p where p.creationDate >= ?1")
    public List<Post> findByDateAfter(Date benchmark); //Get all the posts made AFTER the given date
}
