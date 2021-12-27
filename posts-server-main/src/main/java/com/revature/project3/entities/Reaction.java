package com.revature.project3.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Entity
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reaction_id")
    private Long reactionId;

//    @ManyToOne
//    @JoinColumn(name = "post_id")
    private Long postId;

//    @ManyToOne
//    @JoinColumn(name = "comment_id")
//    private Long commentId;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
    private Long userId;

    private int reaction;

   
}
