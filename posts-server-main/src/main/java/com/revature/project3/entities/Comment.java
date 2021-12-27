package com.revature.project3.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.Comparator;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@ToString
public class Comment implements Comparable<Comment> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    // data when this comment was posted:
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date date;
    // the user who created this comment:
    private String author;


    @Override
    public int compareTo(Comment other) {
        return this.date.compareTo(other.getDate());
    }
}
