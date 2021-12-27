package com.revature.project3.services;

import com.revature.project3.entities.Reaction;
import org.springframework.stereotype.Service;

@Service
public interface ReactionsService {
    Reaction addReaction(Reaction reaction);
    Reaction getReactionByReactionId(Long reactionId);
    Reaction getReactionByPostId(Long postId);
    Reaction updateReaction(Reaction reaction, Long reactionId);
    String deleteReaction(Long reactionId);
}
