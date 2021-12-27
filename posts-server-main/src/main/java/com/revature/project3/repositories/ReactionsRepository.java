package com.revature.project3.repositories;

import com.revature.project3.entities.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactionsRepository extends JpaRepository<Reaction, Long> {
    @Query("select r from Reaction r where r.postId = ?1")
    Reaction findByPostId(Long postId);
    //Reaction findReactionByReactionId(Long reactionId);
}
