package com.goodreads.demo.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@Data
@Builder
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int rating;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User reviewAuthor;

    private LocalDateTime publishedTimestamp;
}
