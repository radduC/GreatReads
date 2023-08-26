package com.goodreads.demo.entities;

import com.goodreads.demo.entities.users.Reader;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@Getter
@Setter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int rating;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "reader_id")
    private Reader reviewAuthor;

    private LocalDateTime publishedTimestamp;
}
